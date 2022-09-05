package duke.exception;

public class InvalidIndexException extends DukeException{
    private static final String errorString = "There is no task with index %d";

    public InvalidIndexException(int index) {
        super(String.format(errorString, index));
    }
}
