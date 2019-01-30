import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class MS3CodingChallenge {
	
	private static SQLiteDB db = new SQLiteDB();
	
	private static CellProcessor[] getProcessors() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	        		
	                new Optional(),//firstName
	                new Optional(),//lastName
	                new Optional(),//email
	                new Optional(),//gender
	                new Optional(),//image
	                new Optional(),//credit card
	                new Optional(),//charge
	                new Optional(),//bool1
	                new Optional(),//bool2
	                new Optional(),//city
	        };
	        
	        return processors;
	}
	
	/* Attempt at using bean reader but run into problem when rows with 11 columns show up at line 3072 in the provided CSV */
	public static void readCSV() throws Exception {
		
		ICsvBeanReader beanReader = null;//SuperCSV bean reader object
		try {
			beanReader = new CsvBeanReader(new FileReader("./ms3Interview.csv"), CsvPreference.STANDARD_PREFERENCE);
			final String[] header = beanReader.getHeader(true);
			final CellProcessor[] processors = getProcessors();
			
			Record record;
			while((record = beanReader.read(Record.class, header, processors)) != null) {
				//record = beanReader.read(Record.class, header, processors);
				System.out.println(String.format("lineNo=%s, rowNo=%s, record=%s", beanReader.getLineNumber(), beanReader.getRowNumber(), record));
			}
			
		} finally {
			
			if(beanReader != null) {
				beanReader.close();
			}
		}
	}
	
	/* Parses the CSV with the list reader and inserts each row into an in-memory SQLite database (Needs to be cleaned up) */
	public static void readCSVListReader(String csvFilePath) throws IOException {
		
		ICsvListReader listReader = null;
        try {
        	listReader = new CsvListReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
                
            listReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
                
            List<Object> recordList;
            while( (listReader.read()) != null ) {
            	
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
            	}
            }
                
        }
        finally {
        	if( listReader != null ) {
        		listReader.close();
        	}
        }
	}
	
	public static List<String[]> readCSVFile(String csvFilePath) {
		
		BufferedReader br = null;
		String line = "";
		String delimiter = ",";//Split data when a non-escaped comma appears
		String[] dataRecord = null;
		List<String[]> arrayListOfRecordArrays = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(csvFilePath));
			while((line = br.readLine()) != null) {
				
				//Separate with delimiter into String array
				dataRecord = line.split(delimiter);//Currently can't differentiate between commas meant as delimiters and commas part of data (Change to third party parser)
				arrayListOfRecordArrays.add(dataRecord);
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} return arrayListOfRecordArrays;//Return the string array containing csv data
	}
	
	public static void main(String[] args) {
		
		String csvFilePath = args[0];//Get the file path as an argument when program is run from terminal
		//List<String[]> csvData;
		//csvData = readCSVFile(csvFilePath);//Call to function that splits up the data into a arraylist of string arrays(Will probably change this)
		
		db.createTable();//Create the table for the SQLite database
		
		//Attempt to parse csv and write to database
		try {
			readCSVListReader(csvFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Set random data record to use to test sqlite database stuff below
		//SQLiteDB db = new SQLiteDB();//Should print success or error
		//db.createTable();//Create a new table in the database
		
		//Loop through records and insert each into database
		//for(int i = 0; i < csvData.size(); i++) {
			//String[] dataRecord = csvData.get(i);//Get the current data record
			//db.insert(dataRecord[0], dataRecord[1], dataRecord[2], dataRecord[3], dataRecord[4], dataRecord[5], dataRecord[6], dataRecord[7], dataRecord[8], dataRecord[9]);//Test inserting a random record into database table called RECORD
		//}
	}
}
