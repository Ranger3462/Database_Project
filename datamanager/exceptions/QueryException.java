package datamanager.exceptions;


/**
 * Thrown when an SQL statemetn fails.
 * 
 * @author S. Sigman 
 * @version 1.0 April 2016
 */
public class QueryException extends RuntimeException
{
    public QueryException(String msg) {
        super(msg);
    }
}
