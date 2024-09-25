package duke.exception;

/**
 * Thrown when MakiBot encounters an issue while executing a command from the user.
 *
 * @author Justin Peng
 */
public class DukeException extends Exception {
    /**
     * Constructs a {@code DukeException} with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
