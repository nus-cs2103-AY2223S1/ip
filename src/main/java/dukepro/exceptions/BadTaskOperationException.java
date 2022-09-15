package dukepro.exceptions;

/**
 * Class for BadTaskOperationException.
 */
public class BadTaskOperationException extends DukeException {
    /**
     * Creates a BadTaskOperationException.
     *
     * @param errorMessage the errormMessage.
     * @return A BadTaskOperationException.
     */
    public BadTaskOperationException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns String version of this.
     *
     * @return A String in specific format.
     */
    @Override
    public String toString() {
        String error = "\n Please provide input that is within the length of the list.\n";
        return error;
    }
}
