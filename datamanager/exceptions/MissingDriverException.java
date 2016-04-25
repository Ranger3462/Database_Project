package datamanager.exceptions;


/**
 * This exception indicates that a data resource driver did not load.
 * 
 * @author S. Sigman 
 * @version 1.0 April 2016
 */
public class MissingDriverException extends RuntimeException
{
    public MissingDriverException(String msg) {
        super(msg);
    }
}
