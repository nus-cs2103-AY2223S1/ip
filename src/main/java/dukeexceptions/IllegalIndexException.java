package dukeexceptions;

/**
 * Thrown when a provided index for a list or array is illegal (out of bounds) or
 * is not an integer.
 */
public class IllegalIndexException extends DukeException {
    /**
     * Constructor for IllegalIndexException.
     */
    public IllegalIndexException() {
        super("The index provided is illegal, or not an integer. Did you enter a non-positive index?");
    }
}
