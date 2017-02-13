package cs565_final;


import java.sql.*;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

//create the class to make transaction table models
public class Transaction implements TableModel {

    CachedRowSet RowSet; // The ResultSet to interpret
    ResultSetMetaData metadata; // Additional information about the results
    int numcols, numrows; // How many rows and columns in the table

    public CachedRowSet getRowSet() {
      return RowSet;
    }

    public Transaction(CachedRowSet rowSetArg) throws SQLException {

      this.RowSet = rowSetArg;
      this.metadata = this.RowSet.getMetaData();
      numcols = metadata.getColumnCount();

      // Retrieve the number of rows.
      this.RowSet.beforeFirst();
      this.numrows = 0;
      while (this.RowSet.next()) {
        this.numrows++;
      }
      this.RowSet.beforeFirst();
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
      this.RowSet.addRowSetListener(listener);
    }

    //insert Transaction row
    public  void insertRow(String customerid, int tdate, String ttype, double price,String stockname,
            double quantity) throws SQLException {

   try {
    this.RowSet.moveToInsertRow();
    this.RowSet.updateString("CustomerId", customerid);
    this.RowSet.updateInt("TransactionDate", tdate);
    this.RowSet.updateString("TransactionType", ttype);
    this.RowSet.updateDouble("Price", price);
    this.RowSet.updateString("StockSymbol", stockname);
    this.RowSet.updateDouble("Quantity", quantity); 
    this.RowSet.insertRow(); 
    this.RowSet.moveToCurrentRow();
   } catch (SQLException e) {
     	e.printStackTrace();
     }
}

    public void close() {
      try {
      	RowSet.getStatement().close();
      } catch (SQLException e) {
      	e.printStackTrace();
      }
    }

    /** Automatically close when we're garbage collected */
    protected void finalize() {
      close();
    }

    /** Method from interface TableModel; returns the number of columns */

    public int getColumnCount() {
      return numcols;
    }

      /** Method from interface TableModel; returns the number of rows */

    public int getRowCount() {
      return numrows;
    }



    public String getColumnName(int column) {
      try {
        return this.metadata.getColumnLabel(column + 1);
      } catch (SQLException e) {
        return e.toString();
      }
    }

    public Class getColumnClass(int column) {
      return String.class;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

      try {
        this.RowSet.absolute(rowIndex + 1);
        Object o = this.RowSet.getObject(columnIndex + 1);
        if (o == null)
          return null;
        else
          return o.toString();
      } catch (SQLException e) {
        return e.toString();
      }
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
    }

    // Because the sample does not allow users to edit any cells from the
    // TableModel, the following methods, setValueAt, addTableModelListener,
    // and removeTableModelListener, do not need to be implemented.

    public void setValueAt(Object value, int row, int column) {
      System.out.println("Calling setValueAt row " + row + ", column " + column);
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }
  }
    
//end of table model
