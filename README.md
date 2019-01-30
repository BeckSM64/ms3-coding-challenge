# MS3 Coding Challenge

This challenge, issued by Mountain State Software Solutions, asks to create a Java Application which takes a CSV file as input, parses the file, and inserts the the data collected into a SQLite database stored in memory. A CSV file that has 10 columns has been provided and only rows which contain 10 columns worth of data should be written to the database. All other data must be written to a separate CSV file labled as bad data with a timestamp of when the file was generated. After parsing the file and writing to the database, a log file stating the number of records that were received, successfully written, and failed to be written must be generated.

## Assumptions

A few assumptions were made when working on this program. The project instructions stated that a 10 column CSV would be provided so the program assumes that the CSV passed in will contain 10 columns. It also assumes that if a column does not contain 10 columns then it contains 11 columns because all "bad data" columns in the CSV contained exactly 11 columns. A valid CSV file will contain a consistent amount of columns per row so this assumption only works in this particutlar instance.

## Building the Project

This project can be built using Maven. You can clone this repository and run the command 

    mvn clean package

in the root of the project.

## Running the Project

The project can then be run by changing to the target directory 

    cd target

and running the generated jar file with the CSV file as an argument 

    java -jar ms3-coding-challenge-1.0.0-SNAPSHOT.jar <relative filepath>
    
ex:

    java -jar ms3-coding-challenge-1.0.0-SNAPSHOT.jar ../ms3Interview.csv

## Approach

To solve this problem, I broke it up into smaller parts which are essentially defined by the project instructions. I knew the program would have to read a CSV file, create and write to an in-memory SQLite database, write some data to a separate CSV file, and write some data to a log file. This would require a CSV parser, a SQLite database, and a method of writing to a log file. I initially chose to write the parser myself and tested it with a test CSV file which contained much less data than the one provided. I then implemented the SQLite database and tested inserting data that was parsed from the test CSV file. For testing purposes I wrote to a database stored on disk so I could easily open and view the file while working on the challenge. I then changed to an in-memory SQLite database later. I knew performance and other issues would come up while using the very limited parser I had built so I opted to switch to SuperCSV and used the CSVListReader and CSVListWriter to parse the CSV and write the bad data to its own separate CSV file. I then implemented a simple method to write the statistics to a text file to complete the challenge.
