package dukeexceptions;

/**
 * Thrown when there is insufficient arguments provided to a function.
 */
public class InsufficientArgumentsException extends DukeException {
    public InsufficientArgumentsException(String msg) {
        super(String.format("Insufficient arguments: %s", msg));
    }
}
