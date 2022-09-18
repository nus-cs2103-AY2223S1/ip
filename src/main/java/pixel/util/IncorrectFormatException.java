package pixel.util;

/**
 * Exception to be thrown if the format of user input is not accepted/ invalid
 */
public class IncorrectFormatException extends RuntimeException {

    private final String errorMessage;

    /**
     * Constructor for a new IncorrectFormatException object
     *
     * @param errorMessage description of the exception
     */
    public IncorrectFormatException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * toString method of the exception
     *
     * @return description of the exception without the package name
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
