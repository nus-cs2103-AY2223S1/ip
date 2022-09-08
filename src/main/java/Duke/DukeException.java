package Duke;

public class DukeException extends Exception {
    /**
     * Creates Duke exception instance.
     * @param message error to print.
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
