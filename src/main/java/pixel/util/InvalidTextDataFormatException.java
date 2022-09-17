package pixel.util;

/**
 * Exception to be thrown if the format of data in text file is invalid
 */
public class InvalidTextDataFormatException extends RuntimeException {

    /**
     * Constructor for a new InvalidTextDataFormatException object
     *
     * @param errorMessage description of the exception
     */
    public InvalidTextDataFormatException(String errorMessage) {
        super(errorMessage);
    }
}
