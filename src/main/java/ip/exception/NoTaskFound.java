package ip.exception;

/**
 * Exception thrown when no task found.
 */
public class NoTaskFound extends DukeException {
    private int index;

    public NoTaskFound(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "No task was found at index: " + ++index;
    }
}
