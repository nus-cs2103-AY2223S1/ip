package exception;

/**
 * When there is an error in the IO of the chatbot
 */
public class MeowerIOException extends MeowerException{
    
    public MeowerIOException(String message) {
        super(message);
    }
}
