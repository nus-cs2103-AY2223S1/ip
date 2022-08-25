package pixel.util;

/**
 * Exception to be thrown if the format of user input is not accepted/ invalid
 */
public class IncorrectFormatException extends RuntimeException {

    /**
     * Constructor for a new IncorrectFormatException object
     *
     * @param errorMessage description of the exception
     */
    public IncorrectFormatException (String errorMessage) {
        super(errorMessage);
    }
}
