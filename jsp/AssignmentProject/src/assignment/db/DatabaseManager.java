package assignment.db;

import java.sql.*;


//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;


public class DatabaseManager {

	private static Connection con;
	private static Statement sql;
	private static DatabaseMetaData dbmd;

	private static final String database = "//localhost:5432/lafworks";
	private static final String username = "postgres";
	private static final String password = "abc";


	public DatabaseManager() throws ClassNotFoundException, SQLException{

	}

	
	//getter for sql	
	public static Statement getSql() throws ClassNotFoundException, SQLException{
		//cannot reuse sql statements. have to reuse every time
		
		//perform a lazy instantiation of the connection
		if(DatabaseManager.sql != null) return con.createStatement();
		else{
	
			System.out.println("Connecting???????????????????");
			Class.forName("org.postgresql.Driver"); //driver for the database manager
			con = DriverManager.getConnection("jdbc:postgresql:"+database, username, password); //connection to the database
			System.out.println("con set");
			dbmd = con.getMetaData();

			System.out.println("Connection to "+ dbmd.getDatabaseProductName() + "  " + 
					dbmd.getDatabaseProductVersion() + " successful");

			sql = con.createStatement();

			System.out.println("inside the databasemanager getSQL() method" + sql);
			
			return sql;
		}
	}
}
