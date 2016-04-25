package datamanager.exceptions;


/**
 * Thrown when a connection to 
 * 
 * @author S. Sigman 
 * @version 1.0 Arpil 2016
 */
public class ConnectionException extends RuntimeException
{
    public ConnectionException(String msg) {
       super(msg);
    }
}
