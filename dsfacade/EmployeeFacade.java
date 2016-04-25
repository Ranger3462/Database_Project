package dsfacade;
import employeemodel.EmployeeSalary;
import datamanager.*;
import datamanager.exceptions.*;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Write a description of class EmployeeFacade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EmployeeFacade
{
    public static List<EmployeeSalary> getEmployeeSalary() {
        // get a data manager
        DataManager manager = null;
        try {
           manager = new DataManager();
        }
        catch (MissingDriverException e) {
           // Fail silently - not a good idea
        }
        
        // Return the employee salary information
        List<EmployeeSalary> empList = new ArrayList<EmployeeSalary>();
        String salQuery = "SELECT ssn, fname, lname, salary from employee";
        List<List<String>> table = manager.doQuery(salQuery);
        
        // process the rows in the table
        Iterator<List<String>> tableIt = table.iterator();
        while (tableIt.hasNext()) {
            // assert: another row
            // retrieve the row
            List<String> row = tableIt.next();
            
            Iterator<String> rowIt = row.iterator();
            // build the employee from the row
            String ssn = rowIt.next();
            String fname = rowIt.next();
            String lname = rowIt.next();
            double salary = Double.parseDouble(rowIt.next());
            
            // create the employee object and add to list of employees
            EmployeeSalary curEmp = new EmployeeSalary(ssn, fname, lname, salary);
            empList.add(curEmp);
        }
        return empList;
    }
}
