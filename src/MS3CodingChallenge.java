import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MS3CodingChallenge {
	
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
		List<String[]> csvData;
		csvData = readCSVFile(csvFilePath);//Call to function that splits up the data into a arraylist of string arrays(Will probably change this)
		
		//Loop to get each data record array
		/*for(int i = 0; i < csvData.size(); i++) {
			
			String[] dataRecord = csvData.get(i);//Get current data record
			
			//Loop through each individual data record array
			for(int j = 0; j < dataRecord.length; j++) {
				System.out.println(j + ": " + dataRecord[j]);//Print each part of the individual record
			}
			System.out.println("\n");//Put a space between each record printed
		}*/
		
		//Set random data record to use to test sqlite database stuff below
		SQLiteDB db = new SQLiteDB();//Should print success or error
		db.createTable();//Create a new table in the database
		
		//Loop through records and insert each into database
		for(int i = 0; i < csvData.size(); i++) {
			String[] dataRecord = csvData.get(i);//Get the current data record
			db.insert(dataRecord[0], dataRecord[1], dataRecord[2], dataRecord[3], dataRecord[4], dataRecord[5], dataRecord[6], dataRecord[7], dataRecord[8], dataRecord[9]);//Test inserting a random record into database table called RECORD
		}
	}
}
