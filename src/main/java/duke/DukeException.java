package duke;

/**
 * Exception class for Duke program
 */
public class DukeException extends Exception {

    /**
     * Constructor for initializing a DukeException
     *
     * @param msg Exception message to be passed.
     */
    public DukeException(String msg) {
        super("\t-----------------------------------------------"
            + "\n\t" + msg
            + "\n\t-----------------------------------------------");
    }
}
