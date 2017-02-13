package cs565_final;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
//for table model
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Date;





public class Brokerage extends JFrame 
{
    public static final int WIDTH = 650;
    public static final int HEIGHT = 700;
	
	 private Connection con;
	 private Mysql mysql;
	 //every trasaction will be charged $10 fee.
	 private static int fee=10;
	 
	 //cardlayout and Jpanel
	 private JPanel Mainpanel;
	 private JPanel Npanel;
	 private JPanel Ntpanel;
	 private JPanel Cpanel;
	 private JPanel Spanel;
	 private JPanel Swpanel;
	 private JPanel Sepanel;
	 private JPanel CstartPanel;
	 private CardLayout Ccard;
	 private CardLayout Scard;
    //button and choice
	 private JButton Transaction;
	 private JButton Customer;
     private JButton Portfolio;
	
	 
	 private TextField Sfday = new TextField(10);
	 private TextField Stday = new TextField(10); 
     private TextField Ssday = new TextField(10); 
     private JButton Searchft = new JButton ("Search within range.");
     private JButton Searchs = new JButton ("Search single day");
     
     //create and initial choice box
     private myChoice startchoice = new myChoice();
     private typeChoice Tchoice = new typeChoice();
     private StypeChoice Schoicettype = new StypeChoice();
     private JTextField textamount = new JTextField("1",10); 
     private JTextField textsname = new JTextField(20); 
     private JTextField textday = new JTextField(10); 
     private JButton makego = new JButton ("Transaction");
     private JButton checkday = new JButton ("Search");

   
    private JTable table; // The table for displaying data
    private JTable table2;
    private JTable Stable;
    private JTable Stable2;
    private JTable Stypetable;
    private JTable Searchtable;
    private JTable Searchtable2;
    private JTable Ptable;
    private JTable SPtable;
    private JTable SearchPtable;
   
    //for input selection from user
    private String choiceresult=null;
    private int Fromday;
    private int Today; 
    private int Sday;
    private int endday;
    private String searchtype;
    
     
     Account AccountTableModel;
     Transaction TransactionTableModel;
     //for selected user
     Account SAccountTableModel;
     Transaction STransactionTableModel;
     //for selected transaction type and date
     Transaction SearchTransactionTableModel;
     Transaction SearchTransactionTableModel2;
     Transaction StypeTableModel;  
     //for selected portfolio table
     Stock PortfolioTableModel;
     Stock SPortfolioTableModel;
     Stock SearchPortfolioTableModel;
    
    //create class implements rowsetListener
    private class rowlisten implements RowSetListener{
    	CachedRowSet rowSet;
    	public rowlisten(CachedRowSet rowSetgiven){
    		this.rowSet=rowSetgiven;
    	}
    	 public void rowSetChanged(RowSetEvent event) {  }

    	    public void rowChanged(RowSetEvent event) { 
    	    	 CachedRowSet currentRowSet = rowSet;

 	    	    try {
 	    	      currentRowSet.moveToCurrentRow();
 	    	      AccountTableModel =
 	    	        new Account(AccountTableModel.getRowSet());
 	    	      table.setModel(AccountTableModel);

 	    	    } catch (SQLException ex) {

 	    	   // Display the error in a dialog box.

 	    	      JOptionPane.showMessageDialog(
 	    	        Brokerage.this,
 	    	        new String[] { // Display a 2-line message
 	    	          ex.getClass().getName() + ": ",
 	    	          ex.getMessage()
 	    	        }
 	    	      );
 	    	    }
    	    }
    	    
    	    public void cursorMoved(RowSetEvent event) { 
    	    	
    	    } 
    }
    	    //for transaction table
    	    //create class implements rowsetListener
    	    private class Trowlisten implements RowSetListener{
    	    	CachedRowSet rowSet;
    	    	public Trowlisten(CachedRowSet rowSetgiven){
    	    		this.rowSet=rowSetgiven;
    	    	}
    	    	 public void rowSetChanged(RowSetEvent event) {  }

    	    	    public void rowChanged(RowSetEvent event) { 
    	    	    	 CachedRowSet currentRowSet = rowSet;

    	 	    	    try {
    	 	    	      currentRowSet.moveToCurrentRow();
    	 	    	      TransactionTableModel =
    	 	    	        new Transaction(TransactionTableModel.getRowSet());
    	 	    	      table2.setModel(TransactionTableModel);

    	 	    	    } catch (SQLException ex) {

    	 	    	   // Display the error in a dialog box.

    	 	    	      JOptionPane.showMessageDialog(
    	 	    	        Brokerage.this,
    	 	    	        new String[] { // Display a 2-line message
    	 	    	          ex.getClass().getName() + ": ",
    	 	    	          ex.getMessage()
    	 	    	        }
    	 	    	      );
    	 	    	    }
    	    	    }
    	    	    
    	    	    public void cursorMoved(RowSetEvent event) { 
    	    	    	
    	    	    } 	
    	    }	    
    	    
       
    
      
    //The main method
    public static void main(String[] args)
    {
    	try{
       	
    	Mysql csql = new Mysql();
    		
        Brokerage gui = new Brokerage(csql.con);
        gui.setVisible(true);
    	}catch (Exception e) {
	    	e.printStackTrace();
	    }
    	
    }
    
    private void createSTableModel()throws SQLException{
    	//For selected customer account table model
    	  
	     try{//Select customer table
	    	 CachedRowSet SmyCachedRowSet = SgetContentsOfAccountTable();
      SAccountTableModel = new Account(SmyCachedRowSet);
      SAccountTableModel.addEventHandlersToRowSet(new rowlisten(SAccountTableModel.RowSet));
      //select transaction table
      CachedRowSet SmyCachedRowSet2 = SgetContentsOfTransactionTable();
      STransactionTableModel = new Transaction(SmyCachedRowSet2);
      STransactionTableModel.addEventHandlersToRowSet(new Trowlisten(STransactionTableModel.RowSet));
      
	     } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	     //for customer
	       Stable = new JTable(); // Displays the table
	        Stable.setModel(SAccountTableModel);
	        Stable.setForeground(Color.blue); 
	      //for transaction  
	        Stable2 = new JTable(); // Displays the table
	        Stable2.setModel(STransactionTableModel);
	        Stable2.setForeground(Color.blue);
	        
	     
	      //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	        //for selected table
		       TablePanel SAccountTablePane = new TablePanel(Stable,testlabel,4);
		      Cpanel.add(SAccountTablePane,"SCustomertable");
		     TablePanel STransactionTablePane = new TablePanel(Stable2,testlabel,4);
	      Cpanel.add(STransactionTablePane,"STransactiontable");
	    
	     
	      Customer.setBackground(Color.WHITE);
   	     Transaction.setBackground(Color.yellow);
   	     Portfolio.setBackground(Color.yellow);
	    
	     	       	     
	  } 
    //select transaction table by choicing a type
    private void createTypeTableModel()throws SQLException{
    	  
	     try{
	    	 CachedRowSet StypeCachedRowSet = StypegetContentsOfTransactionTable();
      StypeTableModel = new Transaction(StypeCachedRowSet);
	     } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	     //for JTable
	       Stypetable = new JTable(); // Displays the table
	        Stypetable.setModel(StypeTableModel);
	        Stypetable.setForeground(Color.blue); 
	      
	      //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	        //for selected table
		       TablePanel StypeTablePane = new TablePanel(Stypetable,testlabel,4);
		      Cpanel.add(StypeTablePane,"Stypetable");   
	  
	        	       	     
	  } 
    //for portfolio table model creation
    private void createPortfolioTableModel()throws SQLException{
    	//For selected customer portfolio table model
    	  
	     try{//Select customer table
	    	 CachedRowSet PmyCachedRowSet = getContentsOfPortfolioTable();
      PortfolioTableModel = new Stock(PmyCachedRowSet);
     
      
	     } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	     //for portfolio
	       Ptable = new JTable(); // Displays the table
	        Ptable.setModel(PortfolioTableModel);
	        Ptable.setForeground(Color.blue); 
	   
	         
	      //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	        //for selected table
		       TablePanel PortfolioTablePane = new TablePanel(Ptable,testlabel,4);
		      Cpanel.add(PortfolioTablePane,"Portfoliotable");
		  
	     
	      Customer.setBackground(Color.yellow);
   	     Transaction.setBackground(Color.yellow);
   	     Portfolio.setBackground(Color.WHITE);
	        	       	     
	  } 
   
    //for portfolio table model of selected customer
    private void ScreatePortfolioTableModel()throws SQLException{
    	//For selected customer account table model
    	  
	     try{  
      //select Portfolio table
      CachedRowSet SPmyCachedRowSet2 = SgetContentsOfPortfolioTable();
      SPortfolioTableModel = new Stock(SPmyCachedRowSet2);
      
	     } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	 
	      //for portfolio  
	        SPtable = new JTable(); // Displays the table
	        SPtable.setModel(SPortfolioTableModel);
	        SPtable.setForeground(Color.blue);
	        
	     
	      //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	        //for selected table
		
		     TablePanel SPortfolioTablePane = new TablePanel(SPtable,testlabel,4);
	      Cpanel.add(SPortfolioTablePane,"SPortfoliotable");
	    
	     
	      Customer.setBackground(Color.yellow);
   	     Transaction.setBackground(Color.yellow);
   	     Portfolio.setBackground(Color.WHITE);
	        	       	     
	  } 
    //for portfolio of selected customer and given day
    private void SearchcreatePortfolioTableModel()throws SQLException{
    	//For selected customer account table model
    	  
	     try{  
      //select Portfolio table
      CachedRowSet SPmyCachedRowSet3 = SearchgetContentsOfPortfolioTable();
      SearchPortfolioTableModel = new Stock(SPmyCachedRowSet3);
      
	     } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	 
	      //create Jtable 
	        SearchPtable = new JTable(); // Displays the table
	        SearchPtable.setModel(SearchPortfolioTableModel);
	        SearchPtable.setForeground(Color.blue);
	        
	     
	      //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	        //for selected table
		
		     TablePanel SearchPortfolioTablePane = new TablePanel(SearchPtable,testlabel,4);
	      Cpanel.add(SearchPortfolioTablePane,"SearchPortfoliotable");
	    
	     
	      Customer.setBackground(Color.yellow);
   	     Transaction.setBackground(Color.yellow);
   	     Portfolio.setBackground(Color.WHITE);
	        	       	     
	  } 
 //for update the transaction table model
    private void createNewTransactionTableModel() throws SQLException {
	    try{     
       //initial transation table
      CachedRowSet myCachedRowSet2 = getContentsOfTransactionTable();
       TransactionTableModel = new Transaction(myCachedRowSet2);
       TransactionTableModel.addEventHandlersToRowSet(new Trowlisten(TransactionTableModel.RowSet));
	     } catch (SQLException e) {
       	e.printStackTrace();
	     } 
	          
       //For new TransactionTableModel
	       table2 = new JTable(); // Displays the table
	        table2.setModel(TransactionTableModel);
	        table2.setForeground(Color.blue);
	        
	       //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
       
	        TablePanel TransactionTablePane = new TablePanel(table2,testlabel,4);
	  
	       Cpanel.add(TransactionTablePane,"Transactiontable");
     } 
    
    
    
    
   //for update the account table model
    private void createNewAccountTableModel() throws SQLException {
    	 //Initial Account table
	    try{ 
    	CachedRowSet myCachedRowSet = getContentsOfAccountTable();
        AccountTableModel = new Account(myCachedRowSet);
        AccountTableModel.addEventHandlersToRowSet(new rowlisten(AccountTableModel.RowSet));
    
	     } catch (SQLException e) {
        	e.printStackTrace();
        }
        
      //set table for customer account table
	        table = new JTable(); // Displays the table
	        table.setModel(AccountTableModel);
	        table.setForeground(Color.blue);   
	          
	        
	       //use customer labels for test
	    	  JLabel label_customerid = new JLabel();
	         JLabel label_customername = new JLabel();
	         JLabel  label_openingdate = new JLabel();
	         JLabel  label_openingbalance = new JLabel();
	       
	        //use tablepanel class to create two table panel.    
	         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};
	         
	         TablePanel AccountTablePane = new TablePanel(table,testlabel,4);
	 	        
	        Cpanel.add(AccountTablePane,"Customertable");
	       
      }
    //create the function to get data from Account table
    public CachedRowSet getContentsOfAccountTable() throws SQLException {
        CachedRowSet crs = null;
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, CustomerName, OpeningDate, OpeningBalance as CurrentBalance from Hu_Accounts");
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    
    //get content of transaction table
    public CachedRowSet getContentsOfTransactionTable() throws SQLException {
        CachedRowSet crs = null;
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);
         	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Transaction

          crs.setCommand("select CustomerId, TransactionDate, TransactionType, Price, StockSymbol, Quantity from Hu_Transactions ");
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    //get contentd of Account table when select a customer
    public CachedRowSet SgetContentsOfAccountTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, CustomerName, OpeningDate, OpeningBalance as CurrentBalance from Hu_Accounts"+" where CustomerName=?");
          crs.setString(1,choiceresult);
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    
    //get contentd of Transaction table when select a customer
    public CachedRowSet SgetContentsOfTransactionTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, TransactionDate, TransactionType, Price, StockSymbol, Quantity from Hu_Transactions where CustomerId=(select CustomerId from Hu_Accounts where CustomerName=?)");
          crs.setString(1,choiceresult);
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    //get content of selected transaction table when searching based on date
    public CachedRowSet SearchgetContentsOfTransactionTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, TransactionDate, TransactionType, Price, StockSymbol, Quantity from Hu_Transactions where (CustomerId=(select CustomerId from Hu_Accounts where CustomerName=?)) and (TransactionDate>=?) and (TransactionDate<=?)");
          crs.setString(1,choiceresult);
          crs.setInt(2,Fromday);
          crs.setInt(3,Today);
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
   
  //get content of selected transaction table2 when searching data based on single day
    public CachedRowSet SearchgetContentsOfTransactionTable2() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, TransactionDate, TransactionType, Price, StockSymbol, Quantity from Hu_Transactions where (CustomerId=(select CustomerId from Hu_Accounts where CustomerName=?)) and (TransactionDate=?)");
          crs.setString(1,choiceresult);
          crs.setInt(2,Sday);     
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    //get content of transaction table by choicing a type of transaction
    public CachedRowSet StypegetContentsOfTransactionTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("select CustomerId, TransactionDate, TransactionType, Price, StockSymbol, Quantity from Hu_Transactions where (CustomerId=(select CustomerId from Hu_Accounts where CustomerName=?)) and (TransactionType=?)");
          crs.setString(1,choiceresult);
          crs.setString(2,searchtype);     
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
 //get content of Portfolio table 
    public CachedRowSet getContentsOfPortfolioTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("SELECT t.StockSymbol,(SUM(CASE WHEN TransactionType = 'Buy' "+
          "THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' "+
        "THEN Quantity ELSE 0 END ))as TotalQuantity,q.StockPrice as CurrentPrice,((SUM(CASE WHEN "+
          "TransactionType = 'Buy' THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' THEN "+
        "Quantity ELSE 0 END ))*q.StockPrice)as CurrentValue FROM Hu_Transactions as t LEFT JOIN "+
          "Hu_StockQuotes as q on t.StockSymbol = q.StockSymbol where t.StockSymbol!='null' GROUP BY t.StockSymbol");
              
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
  //get content of Portfolio table for selected customer
    public CachedRowSet SgetContentsOfPortfolioTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("SELECT t.StockSymbol,(SUM(CASE WHEN TransactionType = 'Buy' "+
                  "THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' "+
                "THEN Quantity ELSE 0 END ))as TotalQuantity,q.StockPrice as CurrentPrice,((SUM(CASE WHEN "+
                  "TransactionType = 'Buy' THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' THEN "+
                "Quantity ELSE 0 END ))*q.StockPrice)as CurrentValue FROM Hu_Transactions as t LEFT JOIN "+
                  "Hu_StockQuotes as q on t.StockSymbol = q.StockSymbol WHERE CustomerId =(select Hu_Accounts.CustomerId from Hu_Accounts where Hu_Accounts.CustomerName=?) And t.StockSymbol!='null' GROUP BY t.StockSymbol");
          crs.setString(1,choiceresult);     
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
  //get content of Portfolio table for selected customer and given day.
    public CachedRowSet SearchgetContentsOfPortfolioTable() throws SQLException {
        CachedRowSet crs = null;
       
        try {
        	
         	RowSetFactory rowSetFactory = RowSetProvider.newFactory(
    				"com.sun.rowset.RowSetFactoryImpl", null);  	
      
          crs = rowSetFactory.createCachedRowSet(); 
    	
          crs.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
          crs.setConcurrency(ResultSet.CONCUR_UPDATABLE);
          crs.setUsername("cs");
          crs.setPassword("java");
          crs.setUrl("jdbc:mysql://localhost:3306/cs565");
          
          // Regardless of the query, fetch the contents of  Accounts

          crs.setCommand("SELECT t.StockSymbol,(SUM(CASE WHEN TransactionType = 'Buy' "+
                  "THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' "+
                "THEN Quantity ELSE 0 END ))as TotalQuantity, SDate as EndDay, q.StockPrice as EndDayPrice,((SUM(CASE WHEN "+
                  "TransactionType = 'Buy' THEN Quantity ELSE 0 END )-SUM(CASE WHEN TransactionType = 'Sell' THEN "+
                "Quantity ELSE 0 END ))*q.StockPrice)as EndDayValue FROM Hu_Transactions as t LEFT JOIN "+
                  "Hu_StockHistory as q on t.StockSymbol = q.StockSymbol WHERE CustomerId =(select Hu_Accounts.CustomerId from Hu_Accounts where Hu_Accounts.CustomerName=?) And t.StockSymbol!='null' And SDate=? GROUP BY t.StockSymbol");
          crs.setString(1,choiceresult);   
          crs.setInt(2,endday);
          crs.execute();

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return crs;
      }
    
  
    //create a JPanel class to show different tables
    private class TablePanel extends JPanel{
    	JTable mytable;
    	JLabel mylabel[];
    	int numlabel;
    	public TablePanel(JTable table, JLabel label[],int num){
    		this.mytable = table;
    		this.mylabel = label;
    		this.numlabel = num;
    		
    		this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.CENTER;
            c.weightx = 0.5;
            c.weighty = 1.0;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            this.add(new JScrollPane(mytable), c);

            for(int i=0;i<numlabel;i++){
            	
            	  c.fill = GridBagConstraints.HORIZONTAL;
                  c.anchor = GridBagConstraints.LINE_START;
                  c.weightx = 0.25;
                  c.weighty = 0;
                  c.gridx = 0;
                  c.gridy = i+1;
                  c.gridwidth = 1;
                  this.add(mylabel[i], c);
                  
            }
          
    		   	}
    	 	
    }
    
    //create choice class of top customer choice to implements itemlistener
    private class myChoice implements ItemListener{
    	
    	Choice mychoice = new Choice();
    	String result="null";
    	
    	public myChoice(){
    		
	        mychoice.setPreferredSize(new Dimension(100, 10));
	        mychoice.addItemListener(this);		      
    		
    	}
    	public void itemStateChanged(ItemEvent ie) { 
    	     result=mychoice.getSelectedItem();
    	     System.out.println("you choice= %s"+result);
    	     choiceresult = result;
    	     
    	     //get SAccountTable
    	   //For selected customer account table model
    	     try{
    	    	 createSTableModel();
    	     }catch(SQLException e) {
    	        	e.printStackTrace();
    	    	 
    	     }
    	     Ccard.show(Cpanel, "SCustomertable");
      
    }
    }
    
    //create choice class of transaction type choice to implements itemlistener
    private class typeChoice implements ItemListener{
    	
    	Choice typechoice = new Choice();
    	String typeresult="Buy";//default;
    	
    	public typeChoice(){
    		
	        //typechoice.setPreferredSize(new Dimension(100, 10));
	        typechoice.addItemListener(this);		      
    		
    	}
    	public void add(String s){
    		typechoice.add(s);
    	}
    	public void itemStateChanged(ItemEvent ie) { 
    	
    	     typeresult=typechoice.getSelectedItem();
    	     searchtype=typeresult;
    	     
    	}
    }
   
  //create choice class of transaction type choice in searching table to implements itemlistener
    private class StypeChoice implements ItemListener{
    	
    	Choice Stypechoice = new Choice();
    	String Styperesult="Buy";//default;
    	
    	public StypeChoice(){
    		
	        //typechoice.setPreferredSize(new Dimension(100, 10));
	        Stypechoice.addItemListener(this);		      
    		
    	}
    	public void add(String s){
    		Stypechoice.add(s);
    	}
    	public void itemStateChanged(ItemEvent ie) { 
    	
    	     Styperesult=Stypechoice.getSelectedItem();
    	     searchtype=Styperesult;
    	     Transaction.setBackground(Color.WHITE);
          	 Customer.setBackground(Color.yellow);
          	 Portfolio.setBackground(Color.yellow);
    	     if(searchtype=="All"){
      		   Ccard.show(Cpanel,"STransactiontable");
      	   }else{
        	     try{
        	    	 createTypeTableModel();
        	     }catch(SQLException e) {
        	        	e.printStackTrace();
        	    	 
        	     }
        	     Ccard.show(Cpanel, "Stypetable");
        	     }
    	     
    	}
    }
    
    
    private void displayExceptionDialog(Exception e) {

        // Display the SQLException in a dialog box
        JOptionPane.showMessageDialog(
          Brokerage.this,
          new String[] {
            e.getClass().getName() + ": ",
            e.getMessage()
          }
        );
      }
      
    //constructor

    public Brokerage(Connection con) throws SQLException
    {
        super("The Brokerage Platform"); 
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.con = con;
        final Connection cc = this.con;
        final DbAccount startaccount = new DbAccount(this.con);
        final DbTransaction myTransaction = new DbTransaction(this.con);
        final DbStock myStock = new DbStock(this.con);
      

        //To create Main Layout.
        Mainpanel = new JPanel();
        Npanel = new JPanel();
        Cpanel = new JPanel();
        CstartPanel = new JPanel();
        Spanel = new JPanel();
        Swpanel = new JPanel();
        Sepanel = new JPanel();
        Mainpanel.setLayout(new BorderLayout( ));
        Spanel.setLayout(new BorderLayout());
        Swpanel.setLayout(new FlowLayout());
        Npanel.setLayout(new BorderLayout( ));
        
        Mainpanel.add(Npanel,BorderLayout.NORTH);
        Mainpanel.add(Cpanel,BorderLayout.CENTER);
        Mainpanel.add(Spanel,BorderLayout.SOUTH);
        Spanel.add(Swpanel,BorderLayout.WEST);
        Spanel.add(Sepanel);
        
        //set background picture
        ImageIcon start5 = new ImageIcon(getClass().getResource("stock4.jpg"));
        JLabel Cstart = new JLabel (start5);
        
        CstartPanel.add(Cstart);
        //set cardlayout.
        Ccard = new CardLayout();
        Scard = new CardLayout();
        
        Cpanel.setLayout(Ccard);
        Sepanel.setLayout(Scard);
       
        Npanel.setPreferredSize(new Dimension(650,80));
        Npanel.setMaximumSize(new Dimension(650,80));
        Npanel.setBackground(new Color(70,130,180));
       
        //create main button
        Transaction = new JButton("Transaction");
        Customer = new JButton("Customer");
        Portfolio = new JButton("Portfolio");
        //create main panels
        Ntpanel = new JPanel();
        Npanel.add(Ntpanel,BorderLayout.CENTER);
        Ntpanel.setBackground(new Color(70,130,180));
        
        JLabel choicename = new JLabel("Customer Name:");
        choicename.setForeground(Color.WHITE);      
        Ntpanel.add(choicename);
         //the Choice box  
        Ntpanel.add(startchoice.mychoice);
        
        JPanel Nnpanel = new JPanel();
        Npanel.add(Nnpanel,BorderLayout.SOUTH);
        Nnpanel.setLayout(new FlowLayout());
        Nnpanel.add(Portfolio,FlowLayout.LEFT);
        Nnpanel.add(Transaction,FlowLayout.LEFT);
        Nnpanel.add(Customer,FlowLayout.LEFT);   
        Nnpanel.setBackground(new Color(70,130,180));
        //set Sepanel button.
        ImageIcon start3 = new ImageIcon(getClass().getResource("transaction.jpg"));
        JButton Maketransaction = new JButton (start3);
        Maketransaction.setPreferredSize(new Dimension(120, 100));
        ImageIcon start4 = new ImageIcon(getClass().getResource("historysearch.jpg"));
        JButton Checkhistory = new JButton (start4);
        
        
        Swpanel.add(Maketransaction);
        Swpanel.add(Checkhistory);
        Swpanel.setPreferredSize(new Dimension(120, 200));
        Border  raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Swpanel.setBorder(raisedetched);
        Swpanel.setBackground(Color.WHITE);
        
        Transaction.setBackground(Color.yellow);
        Customer.setBackground(Color.yellow);
        Portfolio.setBackground(Color.yellow);
        
      //Menu create
        JMenu Menu = new JMenu("File");
      
        JMenuItem Choice1 = new JMenuItem("Load From Database");
      //Add action to each menu choice.
        Choice1.addActionListener(new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		
        		try {      							
        		
      			      myTransaction.dropTable();
      			      startaccount.dropTable();
      			      myStock.dropStockQuotesTable();
      			      myStock.dropStockHistoryTable();
  			     
      			     startaccount.createTable();     						      
      			      startaccount.populateTable();
      			     myTransaction.createTable();     						      
     			     myTransaction.populateTable();
     			     myStock.createStockQuotesTable();
     			     myStock.populateStockQuotesTable();
     			     myStock.createStockHistoryTable();
     			     myStock.populateStockHistoryTable();
     			     //clear orignial choice box
     			     startchoice.mychoice.removeAll();
     			 
     			    try{
     			    	 Statement stmt = cc.createStatement();
     			    String sql = "Select Customername from Hu_Accounts";
     				ResultSet rs = stmt.executeQuery(sql);	
     				startchoice.mychoice.add("NULL");
     				while (rs.next())
     				{
     					startchoice.mychoice.add(rs.getString("Customername"));			 
     				}
     				stmt.close();   				 
     						
     			} catch (SQLException q) {
     				q.printStackTrace();
     			}
       			      
     			      createNewAccountTableModel();
     			      createNewTransactionTableModel();
		          			    			      
        	}catch (Exception f) {
		    	f.printStackTrace();
		    } 
        		   		
        		Ccard.show(Cpanel,"Customertable");
        		Customer.setBackground(Color.WHITE);
       	     Transaction.setBackground(Color.yellow);
       	     Portfolio.setBackground(Color.yellow);
        	}
        });
                  
        Menu.add(Choice1);

        JMenuItem Choice2 = new JMenuItem("Add Customer");
        Choice2.addActionListener(new ActionListener() {

        	            public void actionPerformed(ActionEvent e) {

        	            	 JTextField id = new JTextField(8);
        	                 JTextField name = new JTextField(8);
        	                 JTextField balance = new JTextField(8);
        	                 
        	                 JPanel myPanel = new JPanel();
        	                 myPanel.setLayout(new GridLayout(4, 1));
        	                 myPanel.add(new JLabel("New Customer ID:"));
        	                 myPanel.add(id);
        	                 myPanel.add(new JLabel("New Customer Name:"));
        	                 myPanel.add(name);
        	                 myPanel.add(new JLabel("Initial Deposit:"));
        	                 myPanel.add(balance);             
        	               

        	                 int result = JOptionPane.showConfirmDialog(null, myPanel, 
        	                          "Please Add a New Customer", JOptionPane.OK_CANCEL_OPTION);
        	                 if (result == JOptionPane.OK_OPTION) {
        	                	 
        	                	 //get current day time
        	                	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        	         		  	String date = sdf.format(new Date()); 
        	         		  	int nowdate = Integer.parseInt(date);
        	              	    
        	                	 double tbalance = Double.parseDouble(balance.getText());
        	                    try {

        	                        AccountTableModel.insertRow(id.getText(),name.getText(),nowdate,tbalance);
        	                      } catch (Exception sqle) {
        	                    	  displayExceptionDialog(sqle);
        	                      }
        	                    //add select item to choice of customer.
        	                    startchoice.mychoice.add(name.getText());     
        	                    //write new line insert into Account Table
        	                    try {
        	        	        	  cc.setAutoCommit (false);     	        	        	  
        	        	            AccountTableModel.RowSet.acceptChanges(cc);       	        	            
        	        	           
        	        	          } catch (SQLException sqle) {
        	        	            displayExceptionDialog(sqle);
        	        	            // Now revert back changes
        	        	            try {
        	        	              createNewAccountTableModel();
        	        	            } catch (SQLException sqle2) {
        	        	              displayExceptionDialog(sqle2);
        	        	              // Now revert back changes
        	        	              try {
        	        	                createNewAccountTableModel();
        	        	              } catch (SQLException sqle3) {
        	        	                displayExceptionDialog(sqle3);
        	        	              }
        	        	            }
        	        	          }
    	                    
        	                 }        
        	            
        	            }
        	          });
        	        
        Menu.add(Choice2);
        
        JMenuItem Choice3 = new JMenuItem("Save to Database");
        Choice3.addActionListener(
        		new ActionListener() {

        	        public void actionPerformed(ActionEvent e) {
        	        	
        	        	try{
        	        		//clear table to clear old data
        	        		Statement stmt = cc.createStatement();
                            String query = "DELETE FROM Hu_Transactions";
                            stmt.executeUpdate(query);
                            Statement stmt2 = cc.createStatement();
                            String query2 = "DELETE FROM Hu_Accounts";
                            stmt2.executeUpdate(query2);
        			    //retrieve all new data from Jtable to Account and Transaction Table
        	        		
        			     for(int i=0;i<table.getRowCount();i++){
        			    	 String a = table.getValueAt(i,0).toString();
        			    	 String b = table.getValueAt(i,1).toString();
        			    	 int c =Integer.parseInt(table.getValueAt(i, 2).toString());
        			    	 double d= Double.parseDouble(table.getValueAt(i,3).toString());
        			    	 startaccount.insertRow(a,b,c,d);
        			    	 
        			     }
        			     
        			    for(int i=0;i<=table2.getRowCount();i++){
        			    	 String a = table2.getValueAt(i,0).toString();
        			    	 int b = Integer.parseInt(table2.getValueAt(i,1).toString());
        			    	 String c = table2.getValueAt(i, 2).toString();
        			    	 double d = Double.parseDouble(table2.getValueAt(i, 3).toString());
        			    	 String f = table2.getValueAt(i,4).toString();
        			    	 double g = Double.parseDouble(table2.getValueAt(i, 5).toString());
        			    	 
        			    	 myTransaction.insertRow(a,b,c,f,g,d);
        			   
        			     }
        			     
        			     
        	        	}catch (SQLException q) {
             				q.printStackTrace();
             			}
       			      
        	        }
        	      }    );
        
        Menu.add(Choice3);
        
        JMenuBar bar = new JMenuBar( );
        bar.add(Menu);
        setJMenuBar(bar);
      
                      
       Cpanel.add(CstartPanel,"Start");
         
       //defalut
       Ccard.show(Cpanel, "Start");
       //set Spanel content.
       JPanel Eform = new JPanel();
       Sepanel.add(Eform,"Eform");
       Eform.setBackground(Color.WHITE);
       Eform.setPreferredSize(new Dimension(650, 200));
      //set choice and button in Eform
        JLabel ttype = new JLabel();
       ttype.setText("Select type of transaction:");
        JLabel amount = new JLabel();
       amount.setText("Enter Quantity of Stock(or deposit price):");
        JLabel stockname = new JLabel();
       stockname.setText("Enter Stock Name:");
        JLabel givenday = new JLabel();
       givenday.setText("Enter given day to check portfolio:");
       //set choice and button in Eform2
       JLabel ttype2 = new JLabel();
       ttype2.setText("Select type of transaction:");
       JLabel ftday = new JLabel();
       ftday.setText("From Day :");
       JLabel today = new JLabel();
       today.setText("To Day :");
       JLabel singleday = new JLabel();
       singleday.setText("The Single Day :");
       
       //set Maketransaction panel.
       JPanel Sepanel1 = new JPanel();
       JPanel Sepanel2 = new JPanel();
       Sepanel1.setLayout(new GridLayout(6, 1));
       Sepanel2.setLayout(new GridLayout(6,1));
       
       Tchoice.add("Buy");
       Tchoice.add("Sell");
       Tchoice.add("Deposit");
       
       //create the button for making transaction 
       makego.setFont(new Font("Verdana",Font.BOLD,14));
       checkday.setFont(new Font("Verdana",Font.BOLD,14));
       makego.setForeground(Color.blue);
       checkday.setForeground(Color.blue);
       
       makego.setPreferredSize(new Dimension(55,15));
       Sepanel1.setPreferredSize(new Dimension(650,50));
       
       //add action listener to these two buttons
       makego.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   makego.setForeground(Color.red);
        	   
        	   String userid="Not Find";
        	   try{//get user id by user name
        		    userid=startaccount.selectData(cc,choiceresult);
        	   }catch(SQLException e){
        		   e.printStackTrace();
        	   }
        	   //get current day time
        	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
   		  	String date = sdf.format(new Date()); 
   		  	int nowdate = Integer.parseInt(date);
        	   
    	   double quantity = Double.parseDouble(textamount.getText());
    	
    	   try {//invoke and update data based on each transaction type
    		  if(Tchoice.typeresult =="Buy"){	
    			  double payamount = myStock.getprice(cc,textsname.getText())*quantity;
    			  double fpayamount = payamount+fee;
    			  double cbalance = startaccount.getbalance(cc, choiceresult);
    			  if(cbalance>=fpayamount){
               TransactionTableModel.insertRow(userid,nowdate,"Buy",0.0,textsname.getText(),quantity);
               startaccount.lossmoney(cc, choiceresult, fpayamount);
    			  }
    			  else{
    				  JOptionPane.showMessageDialog(
    				          Brokerage.this,
    				          new String[] {
    				           "You dont have enough Money. Cannot buy."
    				          }
    				        );
    			  }
    		  }else if(Tchoice.typeresult =="Sell"){
    			  double sellamount = myStock.getprice(cc,textsname.getText())*quantity;
    			  double fsellamount = sellamount-fee;
    			  if(quantity<=myTransaction.getquantity(cc, textsname.getText(), userid)){
    			  TransactionTableModel.insertRow(userid,nowdate,"Sell",0.0,textsname.getText(),quantity);
    			  startaccount.addmoney(cc, choiceresult, fsellamount);
    			  //quantity update
    			  }else{
    				  JOptionPane.showMessageDialog(
    				          Brokerage.this,
    				          new String[] {
    				           "You dont have enough stock. Cannot sell."
    				          }
    				        );
    			  }
      		  }else{
    			  TransactionTableModel.insertRow(userid,nowdate,"Deposit",quantity,"null",0.0); 
    			  startaccount.addmoney(cc, choiceresult, quantity);
    			  
    		  }
              
             } catch (Exception sqle) {
           	  displayExceptionDialog(sqle);
             }
    	   try {
	        	  cc.setAutoCommit (false);       	  
	            TransactionTableModel.RowSet.acceptChanges(cc);
	            createNewAccountTableModel();
	          
	          } catch (SQLException sqle) {
	            displayExceptionDialog(sqle);
	            // Now revert back changes
	            try {
	              createNewTransactionTableModel();
	            } catch (SQLException sqle2) {
	              displayExceptionDialog(sqle2);
	              // Now revert back changes
	              try {
	                createNewTransactionTableModel();
	              } catch (SQLException sqle3) {
	                displayExceptionDialog(sqle3);
	              }
	            }
	          }
         
           //jump to child Jtable to show this customer portfolio
    	   try{
    		   createSTableModel();
    	   }catch(SQLException s){
    		   s.printStackTrace();
    	   }
           Ccard.show(Cpanel,"STransactiontable");
           Transaction.setBackground(Color.WHITE);
           Customer.setBackground(Color.yellow);
           Portfolio.setBackground(Color.yellow);
           }      
     });
       //for button checkday add actionlistener
       checkday.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   Transaction.setBackground(Color.yellow);
            	 Customer.setBackground(Color.yellow);
            	 Portfolio.setBackground(Color.WHITE);
          	   checkday.setForeground(Color.red);
          	   
          	    endday=Integer.parseInt(textday.getText());
          	    
          		   try{
          			 SearchcreatePortfolioTableModel();
          		   }catch(SQLException s){
            		   s.printStackTrace();
          			   
          		   }
        	      	   
        	   Ccard.show(Cpanel, "SearchPortfoliotable");
           }
       });
       //create the layout of making transaction and checking .
       Sepanel1.add(ttype);
       Sepanel1.add(Tchoice.typechoice);
       Sepanel1.add(amount);
       Sepanel1.add(textamount);
       Sepanel1.add(stockname);
       Sepanel1.add(textsname);
       Sepanel1.add(new JLabel(""));
       Sepanel1.add(makego);
       Sepanel1.add(givenday);
       Sepanel1.add(textday);
       Sepanel1.add(new JLabel(""));
       Sepanel1.add(checkday);
       
       //set Check transaction History panel.
      
       Schoicettype.add("Buy");
       Schoicettype.add("Sell");
       Schoicettype.add("Deposit");
       Schoicettype.add("All");
        
       Searchft.setFont(new Font("Verdana",Font.BOLD,14));
       Searchs.setFont(new Font("Verdana",Font.BOLD,14));
       Searchft.setForeground(Color.blue);
       Searchs.setForeground(Color.blue);
       
       Searchft.setPreferredSize(new Dimension(55,15));
       Sepanel2.setPreferredSize(new Dimension(650,50));
       
       Sepanel2.add(ttype2);
       Sepanel2.add(Schoicettype.Stypechoice);
       Sepanel2.add(ftday);
       Sepanel2.add(Sfday);
       Sepanel2.add(today);
       Sepanel2.add(Stday);
       Sepanel2.add(new JLabel(""));
       Sepanel2.add(Searchft);
       Sepanel2.add(singleday);
       Sepanel2.add(Ssday);
       Sepanel2.add(new JLabel(""));
       Sepanel2.add(Searchs);
         
       Sepanel.add(Sepanel2,"Sepanel2");
       Sepanel.add(Sepanel1,"Sepanel1");
       //add action listener to searchft button
       Searchft.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   Transaction.setBackground(Color.WHITE);
          	 Customer.setBackground(Color.yellow);
          	 Portfolio.setBackground(Color.yellow);
        	   Searchft.setForeground(Color.red);
        	   
        	    Fromday=Integer.parseInt(Sfday.getText());
        	     Today= Integer.parseInt(Stday.getText());
        		   try{
        			 //select search transaction table
        			      CachedRowSet SearchmyCachedRowSet = SearchgetContentsOfTransactionTable();
        			      SearchTransactionTableModel = new Transaction(SearchmyCachedRowSet);
        			    
        			 //for search transaction  
        				        Searchtable = new JTable(); // Displays the table
        				        Searchtable.setModel(SearchTransactionTableModel);
        				        Searchtable.setForeground(Color.blue);

        			//use customer labels for test
        				    	  JLabel label_customerid = new JLabel();
        				         JLabel label_customername = new JLabel();
        				         JLabel  label_openingdate = new JLabel();
        				         JLabel  label_openingbalance = new JLabel();
        				       
        				        //use tablepanel class to create two table panel.    
        				         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};

        			 TablePanel SearchTransactionTablePane = new TablePanel(Searchtable,testlabel,4);
        				      Cpanel.add(SearchTransactionTablePane,"Searchtransactiontable"); 
        			   		   
            	   }catch(SQLException s){
            		   s.printStackTrace();
            	   }		       
           Ccard.show(Cpanel,"Searchtransactiontable");
        
       }
                
     });
       //add listener to Searchs button
       Searchs.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   Transaction.setBackground(Color.WHITE);
          	 Customer.setBackground(Color.yellow);
          	 Portfolio.setBackground(Color.yellow);
        	   Searchs.setForeground(Color.red);
        	  
        	    Sday=Integer.parseInt(Ssday.getText());
        	   
        		   try{
        			 //select search single day transaction table
        			      CachedRowSet SearchmyCachedRowSet2 = SearchgetContentsOfTransactionTable2();
        			      SearchTransactionTableModel2 = new Transaction(SearchmyCachedRowSet2);

        			 //for search single day transaction  
        				        Searchtable2 = new JTable(); // Displays the table
        				        Searchtable2.setModel(SearchTransactionTableModel2);
        				        Searchtable2.setForeground(Color.blue);

        			//use customer labels for test
        				    	  JLabel label_customerid = new JLabel();
        				         JLabel label_customername = new JLabel();
        				         JLabel  label_openingdate = new JLabel();
        				         JLabel  label_openingbalance = new JLabel();
        				       
        				        //use tablepanel class to create two table panel.    
        				         JLabel[] testlabel = {label_customerid,label_customername,label_openingdate,label_openingbalance};

        			 TablePanel SearchTransactionTablePane2 = new TablePanel(Searchtable2,testlabel,4);
        				      Cpanel.add(SearchTransactionTablePane2,"Searchtransactiontable2"); 
        			   		   
            	   }catch(SQLException s){
            		   s.printStackTrace();
            	   }		       
           Ccard.show(Cpanel,"Searchtransactiontable2");
         
           
       }
                
     });
       
     
       //show initial cardlayout for panel on buttom before used
       JPanel Pwelcome = new JPanel();
       Pwelcome.setLayout(new BorderLayout( ));
       JLabel welcome = new JLabel();
       welcome.setText("Use button on left side to make transaction & check stock.");
       welcome.setForeground(Color.WHITE);
       Sepanel.add(Pwelcome,"Pwelcome");
       Pwelcome.add(welcome,BorderLayout.NORTH);
       Pwelcome.setBackground(new Color(70,130,180));
       welcome.setFont(new Font("Georgia", Font.PLAIN, 19));
       //Make Animation for srcolling text !!!
       scroll st = new scroll("Welcome and Enjoy Java ~~~");
       Pwelcome.add(st,BorderLayout.SOUTH);

      
       Scard.show(Sepanel, "Pwelcome"); 
       Sepanel1.setBackground(Color.WHITE);
       Sepanel2.setBackground(Color.WHITE);
     
     //Set Swpanel button ActionListener
       
       Maketransaction.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   Scard.show(Sepanel, "Sepanel1"); 
        	   }
           
     });
       
       Checkhistory.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   Scard.show(Sepanel, "Sepanel2"); 
           }
     });
       //set top button "customer" ActionListener
       Customer.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   if(choiceresult==null ||choiceresult=="NULL"){
        	   
        	   Ccard.show(Cpanel, "Customertable"); 
        	   }
        	   else{
        		   Ccard.show(Cpanel,"SCustomertable");
        	   }
        	   Transaction.setBackground(Color.yellow);
          	 Customer.setBackground(Color.WHITE);
          	 Portfolio.setBackground(Color.yellow);
           }
     });
       //set top button "transaction" ActionListener
       Transaction.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   if(choiceresult==null ||choiceresult=="NULL"){
            	   
            	   Ccard.show(Cpanel, "Transactiontable"); 
            	   }
            	   else{
            		   Ccard.show(Cpanel,"STransactiontable");
            	   }
        	 Transaction.setBackground(Color.WHITE);
        	 Customer.setBackground(Color.yellow);
        	 Portfolio.setBackground(Color.yellow);
           }
     });
       //set top button "Portfolio" ActionListener
       Portfolio.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	 
        	   if(choiceresult==null ||choiceresult=="NULL"){
        		   try{
            		   createPortfolioTableModel();
            	   }catch(SQLException e) {
       	        	e.printStackTrace();
            		     
            	   }
        	   Ccard.show(Cpanel, "Portfoliotable"); 
        	   }
        	   else{
        		   
        		   try{
            		   ScreatePortfolioTableModel();
            	   }catch(SQLException e) {
       	        	e.printStackTrace();
            		     
            	   }
        		   Ccard.show(Cpanel,"SPortfoliotable");
        	   }
        	   Transaction.setBackground(Color.yellow);
          	 Customer.setBackground(Color.yellow);
          	 Portfolio.setBackground(Color.WHITE);
           }
     });
            
              add(Mainpanel);     
             
    }
//end constructor
  
    
    public void actionPerformed(ActionEvent event) {  }

   
    }
    
    
    
    
    
    

