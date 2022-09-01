package duke;

/**
 * The unique exceptions thrown by Duke.
 */
public class DukeException extends RuntimeException {
    protected String description;

    /**
     * Constructor for DukeException for an invalid input.
     */
    public DukeException() {
        super();
        this.description = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Constructor for DukeException for a missing task descriptor.
     * @param task The task which is missing a descriptor.
     */
    public DukeException(String task) {
        super();
        this.description = "OOPS!!! The description of a " + task + " cannot be empty.";
    }

    /**
     * Returns the description of this DukeException.
     * @return description of this DukeException.
     */
    public String getDescription() {
        return this.description;
    }
}
