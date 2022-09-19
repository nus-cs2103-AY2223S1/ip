package exception;

/**
 * Main exception class from which all custom chatbot exceptions will extend from
 */
public class MeowerException extends Exception{
    
    public MeowerException(String message) {
        super(message);
    }
}
