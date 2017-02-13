package cs565_final;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Calendar;



public class DbTransaction {
	
	 private Connection con;
	 //constructor
	 public DbTransaction(Connection connArg) {
		    super();
		    this.con = connArg;
		    
		  }
	 
	 
	//create transaction table
	  public void createTable() {
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
	    		
			 stmt = con.createStatement();
			 String sqlCreateStatement = "CREATE TABLE Hu_Transactions " +
						"(CustomerId char(10) NOT NULL," +
						"TransactionDate integer NOT NULL," +
						"TransactionType char(10) NOT NULL,"+
						"StockSymbol varchar(40),"+
						"Quantity double,"+
						"Price double,"+
						"FOREIGN KEY (CustomerId) REFERENCES Hu_Accounts (CustomerId))";
						
			 stmt.executeUpdate(sqlCreateStatement);
					stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	  }
	  
	
	//insert into data. 
	  public void populateTable(){
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
			 stmt = con.createStatement();
			    String sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('01', 20140416, 'Deposit', null, 0.0, 230.5)";
				stmt.executeUpdate(sql);
			     sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('01', 20140418, 'Buy', 'IBM', 5.0, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('02', 20140420, 'Deposit', null , 0.0, 220.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('01', 20140423, 'Buy', 'IBM', 2.0, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('03', 20140421, 'Deposit', null, 0.0, 430.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('02', 20140419, 'Buy', 'GE', 5.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('01', 20140418, 'Buy', 'GE', 1.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('04', 20140419, 'Buy', 'FaceBook', 2.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('05', 20140418, 'Buy', 'FaceBook', 3.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('03', 20140425, 'Buy', 'Google', 2.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('05', 20140420, 'Sell', 'FaceBook', 1.00, 0.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values ('04', 20140425, 'Deposit', null, 0.0, 100.0)";
				stmt.executeUpdate(sql);
				
				stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } 
	  }
	//insert row
	  public void insertRow(String customerid,int tdate, String ttype, String sname, double quantity, double price ) throws SQLException {
        
        	String sql = "insert into Hu_Transactions (CustomerId,TransactionDate,TransactionType,StockSymbol,Quantity,Price) values (?, ?, ?, ?,?,?)";
    	    try {	
    	    	PreparedStatement stmt = con.prepareStatement(sql);
    	    	stmt.setString(1, customerid);
    	    	stmt.setInt(2,tdate);
    	    	stmt.setString(3, ttype);
    	    	stmt.setString(4,sname);
    	    	stmt.setDouble(5,quantity);
    	    	stmt.setDouble(6, price);
    	    	
    	    	stmt.executeUpdate();

    		    stmt.close();
    } catch (SQLException e) {
    e.printStackTrace();
    } 
}
	  
	  //to check the stock and quantity information for specific customer
	  public int getquantity(Connection con,String stockname,String userid) throws SQLException
	  {
		  Connection csql =con;
		  String s =stockname;
		  String id = userid;
		
		  int quantity=0;
		 
		  String sql;
		  Statement stmt = csql.createStatement();
		  try{
			  sql="Select SUM(CASE WHEN TransactionType = 'Buy' "+
                  "THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' "+
                "THEN Quantity ELSE 0 END ) from Hu_Transactions where CustomerId='"+id+"' and StockSymbol='"+s+"'";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
		      quantity = rs.getInt(1);
		     
			  }		 		  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	    return quantity;
	  }  
	  
//drop transaction table  
	  public void dropTable() throws SQLException {
		    Statement stmt = null;
		    try {
		      stmt = con.createStatement();
		      stmt.executeUpdate("DROP TABLE IF EXISTS Hu_Transactions");
		     
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } 
		  }
	
	
}