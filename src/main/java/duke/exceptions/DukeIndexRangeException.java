package duke.exceptions;

/**
 * Exception for user input for task index that are not in range.
 */
public class DukeIndexRangeException extends DukeException {
    private String command;
    private int max;

    /**
     * Constructs a new DukeIndexRangeException.
     *
     * @param command Command which triggered the exception.
     * @param given User input for the index.
     * @param max Maximum range for indices.
     */
    public DukeIndexRangeException(String command, int given, int max) {
        super(String.format("Invalid parameter: %d", given + 1));
        this.max = max;
        this.command = command;
    }

    /**
     * Returns the exception message.
     *
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + String.format("Allowed Range for %s: 1 to %d", command, max);
    }
}
