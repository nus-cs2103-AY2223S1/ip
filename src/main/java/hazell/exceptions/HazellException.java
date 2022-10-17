package hazell.exceptions;

/**
 * Base class which other chatbot-related exceptions inherit from.
 */
public class HazellException extends Exception {
    @Override
    public String toString() {
        return ":-( OOPS!!";
    }
}
