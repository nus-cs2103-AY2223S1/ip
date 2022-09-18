package exception;

/**
 * Main exception class from which all custom chatbot exceptions will extend from
 */
public class DukeException extends Exception{
    
    public DukeException(String message) {
        super(message);
    }
}
