public class DukeException extends Exception {
    /**
     * Constructor for the DukeException exception.
     *
     * @param errorMessage the message to show for the current exception
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
