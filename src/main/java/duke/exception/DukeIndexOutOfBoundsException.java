package duke.exception;

/**
 * A DukeIndexOutOfBoundsException is thrown when the index inputted by the user is not within the allowed range.
 */
public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(int size) {
        super(String.format("Please choose a number from 1 to %d", size));
    }
}
