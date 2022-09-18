package exception;

/**
 * When there is an error in the IO of the chatbot
 */
public class DukeIOException extends DukeException{
    
    public DukeIOException(String message) {
        super(message);
    }
}
