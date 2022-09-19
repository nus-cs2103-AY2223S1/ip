package exception;

/**
 * When the user is missing arguments when calling commands
 */
public class MissingArgumentException extends MeowerException {
    
    public MissingArgumentException(String message) {
        super(message);
    }
}
