package cs565_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Mysql {
	//set the path and database name , username and passward.
	 private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		
		private static String DB_URL = "jdbc:mysql://localhost:3306/cs565";
		 static String DB_USERNAME = "cs";
		private static String DB_PASSWORD = "java";
		public static Connection con;
		
		public Mysql(){
			try{
				Class.forName(JDBC_DRIVER);
		//connecting database
		 con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		 
		}catch (Exception e) {
	    	e.printStackTrace();
	    }
		}

}
