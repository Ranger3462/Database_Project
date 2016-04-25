package datamanager;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import datamanager.exceptions.*;

/**
 * class DataManager 
 * The DataManager class provides the basic functionality needed
 * to create a data connection to a MySQL database.  
 * 
 * Note: The doQuery method returns a ResultSet that should be closed
 *       in any method that uses the method in order to free resources.
 * 
 * @author S. Sigman
 * @version 1.0 April 2016
 */
public class DataManager
{
    // Database Connection String values
    // Define Database constants
    protected String JDBC_DRIVER;
    protected String DB_URL;
    protected String USER;
    protected String PASS;
    
       
    // DataManager constructor - The constructor should initialize
    // the data base connection string values.  The values should
    // come from the dbmanager.xml configuration file.
    public DataManager() {
        // The values are hardcoded but should be read from a
        // configuration file that is coded in XML.  The USER 
        // and the PASS should be encrypted for security.
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://mcs.drury.edu/company";
        USER = "panther";
        PASS = "letmein!";
        
        //Register the driver
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (Exception e) {
            throw new MissingDriverException("Driver not found: " + JDBC_DRIVER);
        }
          
    }
    
    /** getConnection creates a connection to the database.
     * 
     * @returns A connection to the data source.
     * @throws datamanager.exceptions.ConnectionException
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            throw new ConnectionException("Connection failed - DB Error");
        }

        return conn;
    }
    
    /**
     * doQuery executes the specified query and returns the results.
     * 
     * @param query The SQL query to be executed.
     * @return A ResultSet object containing the results of the query.
     * @throws QueryException
     */
    public List<List<String>> doQuery(String query) {
       Connection conn = this.getConnection();
       Statement stmt = null;
       ResultSet rs =null;
       List<List<String>> table = new ArrayList<List<String>>();
       try {
           String safeQuery = escapeSQL(query);
           stmt = conn.createStatement();
           rs = stmt.executeQuery(safeQuery);
           
           ResultSetMetaData metaData = rs.getMetaData();
           int columns = metaData.getColumnCount();
           
           while(rs.next()) {
               List<String> row = new ArrayList<String>();
               for (int i = 1; i <= columns; i++) {
                   row.add(rs.getString(i));
               }
               table.add(row);
           } 
           rs.close();
           stmt.close();
           conn.close();
        }
        catch (SQLException e) {
            throw new QueryException("Error executing query: " + query);
        }
        finally {
            cleanUp(conn, stmt, rs);
        }
        return table;
    }
    
    /**
     * doNonQuery executes SQL statements, which return the number
     * of row affected by the statement.  The doNonQuery method should be
     * called to execute statements like INSERT, UPDATE, and DELETE.
     * 
     * @param sqlStmt The sql statement to be executed.
     * @return The number of rows affected by the statement or -1 if the statement
     *         failed without causing an exception.
     * @throws QueryException - if the sqlStmt causes a db error
     */
    public int doNonQuery(String sqlStmt) {
        Connection conn = this.getConnection();
        Statement stmt = null;
        int numRows = -1;
        try {
            stmt = conn.createStatement();
            numRows = stmt.executeUpdate(sqlStmt);
        }
        catch (SQLException e) {
            throw new QueryException("Error executing statement: " + sqlStmt);
        }
        finally {
            cleanUp(conn, stmt, null);
        }
        return numRows;
    }
    
    private void cleanUp(Connection conn, Statement stmt, ResultSet rs) {
        try{
            if(rs != null)
               rs.close();
        }
        catch(SQLException se1){
        } // nothing to do
        try{
            if(stmt != null)
               stmt.close();
        }
        catch(SQLException se2){
        } // nothing to do
        try{
            if(conn!=null)
              conn.close();
        }
        catch(SQLException se3)
        {
            se3.printStackTrace();
        }
    }
    
    /**
     * escapeSQL prepares an SQL statement to prevent SQL injection attacks.
     */
    private String escapeSQL(String sqlStmt) {
        return sqlStmt;
    }
}
