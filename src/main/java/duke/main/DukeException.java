package duke.main;

/**
 * A class to handle Duke Exception.
 */
public class DukeException extends Exception {
    private String description;

    /**
     * Constructs Duke Exception.
     *
     * @param description the description of the exception.
     */
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
