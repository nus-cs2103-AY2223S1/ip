package dukepro.exceptions;

/**
 * Class for EmptyDescException.
 */
public class EmptyDescException extends DukeException {
    private static final String LINE = "_______________________________________";

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
        String ret = LINE + "\n" + "OHNOOOOOO!!! The description of a " + this.getMessage()
                + " cannot be empty.\n" + LINE;
        return ret;
    }
}
