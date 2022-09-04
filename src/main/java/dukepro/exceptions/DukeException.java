package dukepro.exceptions;

/**
 * Class for DukeException.
 */
public class DukeException extends Exception {
    private static final String LINE = "_______________________________________";

    /**
     * Creates a DukeException.
     *
     * @param errorMessage the errormMessage.
     * @return A DukeException.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns String version of this object.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        String ret = LINE + "\nohai, please provide proper input :<<<<<\n" + LINE;
        return ret;
    }
}
