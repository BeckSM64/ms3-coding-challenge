import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class MS3CodingChallenge {
	
	private static SQLiteDB db;//In memory sqlite database to write csv data to
	private static int recordsReceived;
	private static int recordsSuccessful;
	private static int recordsFailed;
	
	/* Get cell processors to match column number and type of data */
	private static CellProcessor[] getProcessors() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	        		
	                new Optional(),//A
	                new Optional(),//B
	                new Optional(),//C
	                new Optional(),//D
	                new Optional(),//E
	                new Optional(),//F
	                new Optional(),//G
	                new Optional(),//H
	                new Optional(),//I
	                new Optional()//J
	        };
	        return processors;
	}
	
	/* Parses the CSV with the list reader and inserts each row into an in-memory SQLite database (Needs to be cleaned up) */
	public static void readCSVListReader(String csvFilePath) throws IOException {
		
		ICsvListReader listReader = null;
        try {
        	listReader = new CsvListReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
                
            listReader.getHeader(true);
                
            List<Object> recordList;
            while( (listReader.read()) != null ) {
            	
            	recordsReceived++;//Increment number of records received when one is encountered
            	CellProcessor[] processors = getProcessors();
            	
            	//Only process rows which contain the correct number of columns (10)
            	if(listReader.length() == 10) {
            		recordList = listReader.executeProcessors(processors);
                	System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
                    listReader.getRowNumber(), recordList));
                	
                	//Check if values are null before calling toString() (Probably a better way to do this)
                	String a, b, c, d, e, f, g, h, i, j;
                	if(recordList.get(0) != null) {
                		a = recordList.get(0).toString();
                	} else {
                		a = null;
                	}
                	
                	if(recordList.get(1) != null) {
                		b = recordList.get(1).toString();
                	} else {
                		b = null;
                	}
                	
                	if(recordList.get(2) != null) {
                		c = recordList.get(2).toString();
                	} else {
                		c = null;
                	}
                	
                	if(recordList.get(3) != null) {
                		d = recordList.get(3).toString();
                	} else {
                		d = null;
                	}
                	
                	if(recordList.get(4) != null) {
                		e = recordList.get(4).toString();
                	} else {
                		e = null;
                	}
                	
                	if(recordList.get(5) != null) {
                		f = recordList.get(5).toString();
                	} else {
                		f = null;
                	}
                	
                	if(recordList.get(6) != null) {
                		g = recordList.get(6).toString();
                	} else {
                		g = null;
                	}
                	
                	if(recordList.get(7) != null) {
                		h = recordList.get(7).toString();
                	} else {
                		h = null;
                	}
                	
                	if(recordList.get(8) != null) {
                		i = recordList.get(8).toString();
                	} else {
                		i = null;
                	}
                	
                	if(recordList.get(9) != null) {
                		j = recordList.get(9).toString();
                	} else {
                		j = null;
                	}
                	
                	db.insert(a, b, c, d, e, f, g, h, i, j);//Insert into sqlite database
                	recordsSuccessful++;//Increment number of records successfull
                	
            	} else if(listReader.length() == 11) {
            		
            		//Use this if columns are equal to 11 for a row
            		processors = new CellProcessor[] { 
        	        		
        	                new Optional(),//A
        	                new Optional(),//B
        	                new Optional(),//C
        	                new Optional(),//D
        	                new Optional(),//E
        	                new Optional(),//F
        	                new Optional(),//G
        	                new Optional(),//H
        	                new Optional(),//I
        	                new Optional(),//J
        	                new Optional()//K (Extra column)
        	        };
            		
            		recordList = listReader.executeProcessors(processors);
            		System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
                            listReader.getRowNumber(), recordList));
            		writeCsv(recordList);
            		recordsFailed++;//Increment number of records failed (Too many columns)
            	}
            }
                
        }
        finally {
        	if( listReader != null ) {
        		listReader.close();
        	}
        }
	}
	
	/* Takes a record as a list of objects and writes them to a csv file with a timestamp */
	public static void writeCsv(List<Object> record) throws IOException {
                
        ICsvListWriter listWriter = null;
        try {
        	Date currentTimestamp = new Date();//Grab the current date
        	String timestampString = currentTimestamp.toString().replace(' ', '-');//Replace spaces with hyphens (string builder instead?)
        	timestampString = timestampString.replace(':', '-');//Replace colons with hyphens
        	//timestampString += System.currentTimeMillis();//Add milliseconds to the end of date so names are unique
        	
        	String writeToFilePath = "bad-data-" + timestampString + ".csv";//Create the name of the file that will be written to
        	listWriter = new CsvListWriter(new FileWriter(writeToFilePath, true),
        	CsvPreference.STANDARD_PREFERENCE);//Create list writer with standard preferences
                
        	final CellProcessor[] processors = new CellProcessor[] { 
	        		
	                new Optional(),//A
	                new Optional(),//B
	                new Optional(),//C
	                new Optional(),//D
	                new Optional(),//E
	                new Optional(),//F
	                new Optional(),//G
	                new Optional(),//H
	                new Optional(),//I
	                new Optional(),//J
	                new Optional()//K (The extra column)
	        };
        	
        	//Column Headers
        	final String[] header = new String[] {"A", "B", "C", "D",
            	"E", "F", "G", "H", "I", "J", "K"};
                
        	//Write the column headers to the specified csv file
        	//listWriter.writeHeader(header);
                
        	//Write the record to the file
        	listWriter.write(record, processors);
                
        } finally {
        	if(listWriter != null) {
        		listWriter.close();
            }
        }
    }
	
	/* Writes statistics to a log file in the same directory as the running program */
	public static void writeLogFile() {
		
		String recordsReceivedString = "Records received: " + Integer.toString(recordsReceived);
		String recordsSuccessfulString = "Records successful: " + Integer.toString(recordsSuccessful);
		String recordsFailedString = "Records failed: " + Integer.toString(recordsFailed);
		
		//Store each string in a list of strings
		List<String> lines = Arrays.asList(recordsReceivedString, recordsSuccessfulString, recordsFailedString);
		Path file = Paths.get("log.txt");//Create the path to log file
		
		//Attempt to write to the log file
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));//Write to the text file using utf-8 encoding
		} catch (IOException e) {
			e.printStackTrace();//Print error if can't write to log file
		}
	}
	
	public static void main(String[] args) {
		
		//Statistic variables (Static for now)
		recordsReceived = 0;
		recordsSuccessful = 0;
		recordsFailed = 0;
		
		String csvFilePath = args[0];//Get the file path as an argument when program is run from terminal
		db = new SQLiteDB();//Create the in memory database to write the CSV records to
		db.createTable();//Create the table for the SQLite database
		
		//Attempt to parse csv and write to database
		try {
			readCSVListReader(csvFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writeLogFile();//Write the statistics to a log file
	}
}
