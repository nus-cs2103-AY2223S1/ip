package duke.exceptions;

/**
 * Representation of an exception where user provides a blank input
 */
public class BlankCommandException extends DukeException {

    public BlankCommandException() {
        super("BROTHER!\nYOU CANNOT GIVE ME BLANK :(");
    }
}
