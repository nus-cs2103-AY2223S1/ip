package Duke.DukeExceptions;

public class DukeException extends Exception {
    private static String line = "_______________________________________";

    /**
     * Constructor for DukeException.
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
        String ret = line + "\nohai, please provide proper input :<<<<<\n" + line;
        return ret;
    }
}