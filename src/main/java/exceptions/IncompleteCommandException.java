package exceptions;

/**
 * IncompleteCommandException describes an error where a command given cannot be
 * understood because there is insufficient arguments to completely parse it
 */
public class IncompleteCommandException extends Exception {

    /**
     * Creates an incomplete command exception
     */
    public IncompleteCommandException() {
        super();
    }

    /**
     * Creates an incomplete command exception
     * @param msg the message of the error
     */
    public IncompleteCommandException(String msg) {
        super(msg);
    }
}
