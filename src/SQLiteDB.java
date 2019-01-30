import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDB {
	
	Connection connection;
	
	public SQLiteDB() {
		
		connection = null;//Initially set connect to null
		
	    try {
	    	//Create a sqlite db called test.db store on disk for now (Just memory later)
	    	Class.forName("org.sqlite.JDBC");
	        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
	        
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
						"(A          TEXT, " +
						" B          TEXT, " +
						" C          TEXT, " +
						" D          TEXT, " +
						" E          TEXT, " +
						" F          TEXT, " +
						" G          TEXT, " +
						" H          TEXT, " +
						" I          TEXT, " +
						" J          TEXT)";//RowID in SQLite is apparently like autoincrement so primary key does not need to be explicitly defined?
	
			//Execute sql statement
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();//Show error
		}
		
		System.out.println("Table created");//Print success message
	}
	
	/* Method to insert a new record into DB. Takes all column fields as parameters. Currently requires all arguments to be passed (No null values) */
	public void insert(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "INSERT INTO RECORD (A, B, C, D, E, F, G, H, I, J) " +
                    "VALUES ('" + a + "', '" + b + "', '" + c + "', '" + d + "', '" + e + "', '" + f + "', '" + g + "', '" + h + "', '" + i + "', '" + j + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			
		} catch (SQLException ex) {
			
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
