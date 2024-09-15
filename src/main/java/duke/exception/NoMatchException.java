package duke.exception;

public class NoMatchException extends DukeException{
    private static final String errorString = "There are no matching results.";

    public NoMatchException() {
        super(errorString);
    }
}
