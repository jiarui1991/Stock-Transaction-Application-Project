package cs565_final;


import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class DbAccount {

	
	 private Connection con;
	 //constructor
	 public DbAccount(Connection connArg) {
		    super();
		    this.con = connArg;
		    
		  }
	 
	 
	//create table
	  public void createTable() {
		  
		//insert value into table
		  Statement stmt = null;
	    try {	
			 stmt = con.createStatement();
			 String sqlCreateStatement = "CREATE TABLE Hu_Accounts " +
						"(CustomerId char(10) NOT NULL primary key," +
						"CustomerName varchar(40) NOT NULL," +
						"OpeningDate integer NOT NULL,"+
						"OpeningBalance double NOT NULL)";

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
				  String sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values ('01', 'Bob Wesely', 20140301, 1020.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values ('02', 'Jackson', 20140305, 1220.5)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values ('03', 'Cherry Wu', 20140302, 2320.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values ('04', 'Anglea ', 20140305, 2390.0)";
				stmt.executeUpdate(sql);
				sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values ('05', 'Mike Brook', 20140227, 1120.0)";
				stmt.executeUpdate(sql);
				
				stmt.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } 
	  }
//insert row
	  public void insertRow(String customerid, String customername, int date, double balance) throws SQLException {
        
        	String sql = "insert into Hu_Accounts (CustomerId,CustomerName,OpeningDate,OpeningBalance) values (?, ?, ?, ?)";
    	    try {	
    	    	PreparedStatement stmt = con.prepareStatement(sql);
    	    	stmt.setString(1, customerid);
    	    	stmt.setString(2,customername);
    	    	stmt.setInt(3, date);
    	    	stmt.setDouble(4,balance);
    	    	
    	    	stmt.executeUpdate();

    		    stmt.close();
    } catch (SQLException e) {
    e.printStackTrace();
    } 
}

  
	  public void dropTable() throws SQLException {
		    Statement stmt = null;
		    try {
		      stmt = con.createStatement();
		      stmt.executeUpdate("DROP TABLE IF EXISTS Hu_Accounts");
		     
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } 
		  }
   //Select customerid by inputing customername. This function will be revoked in Brokerage.
	  public  static String selectData(Connection con,String username) throws SQLException 
		{
		  Connection csql = con;
		  String u = username;
		  String id="Not Find";
		  String sql;
		  Statement stmt = csql.createStatement();
			try{
				sql = "Select CustomerId from Hu_Accounts where CustomerName='"+u+"'";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next())
				{
					id = rs.getString(1);
				}
				stmt.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			return id;
		}
	  //To deposit money to selected customer when deposit action is triggered.
	  public void addmoney(Connection con,String username,double money) throws SQLException
	  {
		  Connection csql =con;
		  String u =username;
		  double m = money;
		  double om=0;
		  double nm;
		  String sql;
		  Statement stmt = csql.createStatement();
		  try{
			  sql="Select OpeningBalance from Hu_Accounts where CustomerName='"+u+"'";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
		      om = rs.getDouble(1);
			  }
			 nm=om+money;
			  sql="Update Hu_Accounts set OpeningBalance='"+nm+"'where CustomerName='"+u+"'";
			  stmt.executeUpdate(sql);
					 		  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	  
	  }
	  //To minutes money in Account table when buying stock  
	  public void lossmoney(Connection con,String username,double money) throws SQLException
	  {
		  Connection csql =con;
		  String u =username;
		  double m = money;
		  double om=0;
		  double nm;
		  String sql;
		  Statement stmt = csql.createStatement();
		  try{
			  sql="Select OpeningBalance from Hu_Accounts where CustomerName='"+u+"'";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
		      om = rs.getDouble(1);
			  }
			 nm=om-money;
			  sql="Update Hu_Accounts set OpeningBalance='"+nm+"'where CustomerName='"+u+"'";
			  stmt.executeUpdate(sql);
					 		  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	  
	  }  
	//get the balance of current account
	  public double getbalance(Connection con,String username) throws SQLException
	  {
		  Connection csql =con;
		  String u =username;
		  double money=0;
		  String sql;
		  Statement stmt = csql.createStatement();
		  try{
			  sql="Select OpeningBalance from Hu_Accounts where CustomerName='"+u+"'";
			  ResultSet rs = stmt.executeQuery(sql);
			  while (rs.next()){
		      money = rs.getDouble(1);
			  }		 		  
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
	    return money;
	  } 
	  
	 
	
}