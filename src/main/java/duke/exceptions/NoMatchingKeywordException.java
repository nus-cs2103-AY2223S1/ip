package duke.exceptions;

public class NoMatchingKeywordException extends DukeException {
    public NoMatchingKeywordException(String msg) {
        super("NO TASK FOUND BASED ON: " + msg);
    }
}
