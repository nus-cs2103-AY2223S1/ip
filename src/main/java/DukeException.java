public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for an instance of and exception.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super("☹ OOPS!!!" + message);
        this.message = message;
    }

    /**
     * Get string representation of the exception.
     *
     * @return String rep of exception
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.message;
    }
}
