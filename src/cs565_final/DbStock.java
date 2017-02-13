package cs565_final;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbStock {
	
	 private Connection con;
	 //constructor
	 public DbStock(Connection connArg) {
		    super();
		    
		    this.con = connArg;
		    
		  }
	 
	 //Manage the StockQuotes table
	//create table
	  public void createStockQuotesTable() {
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
	    		
			 stmt = con.createStatement();
			 String sqlCreateStatement = "CREATE TABLE Hu_StockQuotes " +
						"(StockSymbol varchar(10) NOT NULL PRIMARY KEY," +
						"StockPrice double)";
						
			 stmt.executeUpdate(sqlCreateStatement);
					stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	  }
	  
	
	//insert into data. 
	  public void populateStockQuotesTable(){
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
			 stmt = con.createStatement();
			    String sql = "insert into Hu_StockQuotes (StockSymbol,StockPrice) values ('IBM', 192.9)";
				stmt.executeUpdate(sql);
			     sql = "insert into Hu_StockQuotes (StockSymbol,StockPrice) values ('GE', 26.7)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockQuotes (StockSymbol,StockPrice) values ('FaceBook', 55.9)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockQuotes (StockSymbol,StockPrice) values ('Google', 522.7)";
				stmt.executeUpdate(sql);

				
				stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } 
	  }




	//check the current price of specific stock.
	  public double getprice(Connection con,String stockname) throws SQLException
	  {
		  Connection csql =con;
		  String s =stockname;
		  double price=0;
		  String sql;
		  Statement stmt = csql.createStatement();
		  try{
			  sql="Select StockPrice from Hu_StockQuotes where StockSymbol='"+s+"'";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
		      price = rs.getDouble(1);
			  }		 		  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	    return price;
	  } 
	  
	  
//drop StockQuotes table 
	  public void dropStockQuotesTable() throws SQLException {
		    Statement stmt = null;
		      stmt = con.createStatement();
		      String sqlDropStatement = "DROP TABLE Hu_StockQuotes";
				try {
					stmt.executeUpdate(sqlDropStatement);
				}
				catch (Exception e1)
				{
					System.out.println("Trying to drop Hu_StockQuptes Table...");
				}
		     
		    
		  }
	
	//manage the Stock History table
	//create table
	  public void createStockHistoryTable() {
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
	    		
			 stmt = con.createStatement();
			 String sqlCreateStatement = "CREATE TABLE Hu_StockHistory " +
						"(StockSymbol varchar(10) NOT NULL," +
					    "SDate integer,"+
						"StockPrice double)";
						
			 stmt.executeUpdate(sqlCreateStatement);
					stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	  }
	  
	
	//insert into data. 
	  public void populateStockHistoryTable(){
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
			 stmt = con.createStatement();
			    String sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140401, 191.4)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140402, 190.3)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140403, 189.9)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140404, 194.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140405, 188.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140406, 192.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140407, 190.3)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140408, 189.9)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140409, 194.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140410, 170.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140411, 182.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140412, 190.3)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140413, 195.9)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140414, 194.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140415, 188.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140416, 192.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140418, 190.3)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140419, 189.9)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140420, 194.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140421, 188.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140422, 192.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140423, 190.3)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140424, 189.9)";
				stmt.executeUpdate(sql);
				 sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140425, 194.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140426, 188.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140427, 192.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('IBM', 20140428, 190.3)";
				stmt.executeUpdate(sql);
				
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140401, 26.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140402, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140403, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140404, 20.4)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140405, 26.3)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140406, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140407, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140408, 20.4)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140409, 26.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140410, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140411, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140412, 20.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140413, 26.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140414, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140415, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140416, 20.4)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140417, 20.4)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140418, 26.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140419, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140420, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140421, 20.4)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140422, 26.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140423, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140424, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140425, 20.4)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140426, 22.4)";
			    stmt.executeUpdate(sql);
			    sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140427, 23.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140428, 20.4)";
			    stmt.executeUpdate(sql);	
			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140401, 55.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140402, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140403, 55.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140404, 56.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140405, 58.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140406, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140407, 51.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140408, 56.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140409, 55.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140410, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140411, 55.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140412, 56.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140413, 58.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140414, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140415, 51.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140416, 56.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140417, 52.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140418, 57.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140419, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140420, 55.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140421, 56.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140422, 58.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140423, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140424, 51.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140425, 56.1)";
			    stmt.executeUpdate(sql);		
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140426, 53.1)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140427, 51.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('FaceBook', 20140428, 56.1)";
			    stmt.executeUpdate(sql);
			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140401, 522.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140402, 520.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140403, 524.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140404, 526.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140405, 522.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140406, 520.1)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140407, 525.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140408, 520.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140409, 520.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140410, 523.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140411, 522.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140412, 529.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140413, 522.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140414, 520.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140415, 524.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140416, 526.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140417, 520.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140418, 519.1)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140419, 525.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140420, 520.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140421, 528.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140422, 523.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140423, 515.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140424, 529.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140425, 528.1)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140426, 523.7)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140427, 515.4)";
			    stmt.executeUpdate(sql);	    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('Google', 20140428, 529.1)";
			    stmt.executeUpdate(sql);
			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140401, 26.7)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140402, 24.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140403, 27.6)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140404, 25.9)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140405, 21.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140406, 28.6)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140407, 26.7)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140408, 24.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140409, 27.6)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140410, 25.9)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140411, 21.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140412, 28.6)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140413, 26.7)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140414, 24.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140415, 27.6)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140416, 25.9)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140417, 21.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140418, 28.6)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140419, 26.7)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140420, 24.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140421, 27.6)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140422, 25.9)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140423, 21.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140424, 28.6)";
			    stmt.executeUpdate(sql);			    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140425, 27.6)";
			    stmt.executeUpdate(sql);	
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140426, 25.9)";
			    stmt.executeUpdate(sql);		    
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140427, 21.7)";
			    stmt.executeUpdate(sql);
				sql = "insert into Hu_StockHistory (StockSymbol,SDate,StockPrice) values ('GE', 20140428, 28.6)";
			    stmt.executeUpdate(sql);			    
			    
				
				stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } 
	  }


//drop StockHistory table
  
	  public void dropStockHistoryTable() throws SQLException {
		    Statement stmt = null;
		      stmt = con.createStatement();
		      String sqlDropStatement = "DROP TABLE Hu_StockHistory";
				try {
					stmt.executeUpdate(sqlDropStatement);
				}
				catch (Exception e1)
				{
					System.out.println("Trying to drop Hu_StockHistory Table...");
				}
		     
		    
		  }  
	
	
	
}
