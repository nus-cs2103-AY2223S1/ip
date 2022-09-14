package duke.exceptions;

/**
 * Representation of an exception where no task matches user's input.
 */
public class NoMatchingKeywordException extends DukeException {
    public NoMatchingKeywordException(String msg) {
        super("NO TASK FOUND BASED ON: " + msg);
    }
}
