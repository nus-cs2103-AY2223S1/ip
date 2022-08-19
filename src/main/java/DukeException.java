public class DukeException extends Exception {
    /**
     *
     * @param message
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
