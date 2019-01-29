import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDB {
	
	private Connection connection;
	
	public SQLiteDB() {
		
		connection = null;//Initially set connect to null
		
	    try {
	    	//Create a sqlite db called test.db store on disk for now (Just memory later)
	    	Class.forName("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite:test.db");
	        
	    } catch (Exception e) {
	    	
	    	//Print error if cannot connect
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    }
	    System.out.println("Connection Successful");//Print success message
	}
	
	public void createTable() {
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "CREATE TABLE RECORD " +
						//"(ID INT PRIMARY KEY   NOT NULL," +
						"(FNAME          TEXT  NOT NULL, " +
						" LNAME          TEXT  NOT NULL, " +
						" EMAIL          TEXT  NOT NULL, " +
						" GENDER         TEXT  NOT NULL, " +
						" IMAGE          TEXT  NOT NULL, " +
						" CREDITCARD     TEXT  NOT NULL, " +
						" CHARGE         TEXT  NOT NULL, " +
						" BOOL1          TEXT  NOT NULL, " +
						" BOOL2          TEXT  NOT NULL, " +
						" CITY           TEXT  NOT NULL)";//RowID in SQLite is apparently like autoincrement so primary key does not need to be explicitly defined?
	
			//Execute sql statement
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();//Show error
		}
		
		System.out.println("Table created");//Print success message
	}
	
	/* Method to insert a new record into DB. Takes all column fields as parameters. Currently requires all arguments to be passed (No null values) */
	public void insert(String fname, String lname, String email, String gender, String image, String creditCard, String charge, String bool1, String bool2, String city) {
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "INSERT INTO RECORD (FNAME, LNAME, EMAIL, GENDER, IMAGE, CREDITCARD, CHARGE, BOOL1, BOOL2, CITY) " +
                    "VALUES ('" + fname + "', '" + lname + "', '" + email + "', '" + gender + "', '" + image + "', '" + creditCard + "', '" + charge + "', '" + bool1 + "', '" + bool2 + "', '" + city + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
