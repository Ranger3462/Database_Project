package employeemodel;


/**
 * The Employee class models an employee.
 * 
 * @author S. Sigman
 * @version 1.0 April 2016
 */
public class EmployeeSalary
{
   private String ssn;
    private String fname;
    private String lname;
    private double salary;
    
    public EmployeeSalary(String ssn, String fname, String lname, double salary) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }
    
    public String getSSN() {
        return ssn;
    }
    
    public String getFName() {
        return fname;
    }
    
    public String getLName() {
        return lname;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String toString() {
        String retStr = String.format("%9s %10s, %10s  %,9.2f",
                                       ssn, lname, fname, salary);
        return retStr;
    }
}
