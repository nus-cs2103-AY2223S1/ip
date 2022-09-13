package duke.exceptions;

/**
 * Representation of an exception where user input complex instruction
 * without description
 */
public class BlankDescriptionException extends DukeException {
    public BlankDescriptionException() {
        super("DESCRIPTION OF TASK CANNOT BE BLANK");
    }
}
