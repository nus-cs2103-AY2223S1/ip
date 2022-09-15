package duke;

/**
 * Represents a DukeException.
 */
public class DukeException extends Exception {
    private String content;

    /**
     * Initialises DukeException.
     * @param content
     */
    public DukeException(String content) {
        this.content = content;
    }

    /**
     * Returns string representation of DukeException.
     * @return String representation of DukeException.
     */
    @Override
    public String toString() {
        return this.content;
    }
}
