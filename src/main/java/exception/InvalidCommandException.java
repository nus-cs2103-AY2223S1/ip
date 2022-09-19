package exception;

/**
 * when an invalid command is given by the user
 */
public class InvalidCommandException extends MeowerException{
    
    public InvalidCommandException(String message) {
        super(message);
    }
}
