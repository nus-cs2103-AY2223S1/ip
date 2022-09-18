package duke;

public class DukeException extends Exception {
    /**
     *
     * @param message shows error message for DukeException
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
