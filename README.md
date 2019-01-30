# MS3 Coding Challenge

This challenge, issued by Mountain State Software Solutions, asks to create a Java Application which takes a CSV file as input, parses the file, and inserts the the data collected into a SQLite database stored in memory. A CSV file that has 10 columns has been provided and only rows which contain 10 columns worth of data should be written to the database. All other data must be written to a separate CSV file labled as bad data with a timestamp of when the file was generated. After parsing the file and writing to the database, a log file stating the number of records that were received, successfully written, and failed to be written must be generated.

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
