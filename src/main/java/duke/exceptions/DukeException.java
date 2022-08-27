package duke.exceptions;

public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}