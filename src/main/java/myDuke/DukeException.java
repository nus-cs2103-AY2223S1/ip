package myduke;
/**
 * This class encapsulates the exceptions that are specific to chatbot
 */
public class DukeException extends Exception{
    /**
     * Constructor for exception
     * @param message what is the error
     */
    public DukeException(String message) {
        super(message);
    }
}
