package dukepro.exceptions;

/**
 * Class for EmptyDescException.
 */
public class EmptyDescException extends DukeException {
    /**
     * Creates a EmptyDescException.
     *
     * @param errorMessage the errormMessage.
     * @return An EmptyDescException.
     */
    public EmptyDescException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns String version of this object.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        String error = "OHNOOOOOO!!! The description of a " + this.getMessage()
                + " cannot be empty.\n";
        return error;
    }
}
