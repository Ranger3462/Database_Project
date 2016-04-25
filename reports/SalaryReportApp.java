package reports;

import dsfacade.EmployeeFacade;
import employeemodel.EmployeeSalary;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

/**
 * This program creates an employee salary report.
 * 
 * @author S. Sigman
 * @version 1.0 April 2016
 */
public class SalaryReportApp
{
    public static void main(String [] args) {
        // get the employees
        List<EmployeeSalary> emps = EmployeeFacade.getEmployeeSalary();
        
        // print the heading information
        System.out.println("Salary Report");
        System.out.println();
        System.out.printf("%-9s %10s  %10s  %-10s\n", 
                    "SSN", "Last Name", "First Name","Salary");
        char [] lineChars = new char[44];
        Arrays.fill(lineChars,'=');
        String line = new String(lineChars);
        System.out.println(line);
        
        // print the body of the report
        Iterator<EmployeeSalary> salIt = emps.iterator();
        while(salIt.hasNext()) {
            System.out.println(salIt.next());
        }
        System.out.println(line);
        
        
    }
}
