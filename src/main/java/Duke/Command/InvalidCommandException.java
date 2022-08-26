package Duke.Command;

/** 
 * This class represents the exceptions that occurs when an
 * invalid command was given by the user.
 */
public class InvalidCommandException extends Exception{
    
    public InvalidCommandException(String errMsg) {
        super(errMsg);
    }
}
