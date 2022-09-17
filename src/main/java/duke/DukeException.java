package duke;

public class DukeException extends Exception {
    /**
     * Creates Duke exception instance.
     * @param message error to print.
     */
    public DukeException(String message) {
        super("Pardon me my lord. " + message);
    }
}
