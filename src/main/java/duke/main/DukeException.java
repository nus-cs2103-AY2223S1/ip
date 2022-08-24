package duke.main;

public class DukeException extends Exception {
    private String description;
    public DukeException(String description) {
        this.description = description;
    }

    /**
     * Returns the exception message.
     *
     * @return the string of error message
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + this.description;
    }
}
