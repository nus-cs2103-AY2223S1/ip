package exception;

/**
 * when an invalid date or non-date format is given by the user
 */
public class InvalidDateException extends MeowerException{
    
    public InvalidDateException(String message) {
        super(message);
    }
}
