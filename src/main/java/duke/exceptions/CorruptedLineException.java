package duke.exceptions;

/**
 * Thrown when a line in the save file is corrupted.
 */
public class CorruptedLineException extends DukeException {
    private static final String DESCRIPTION = "There was an error parsing this line";

    public CorruptedLineException() {
        super(DESCRIPTION);
    }
}
