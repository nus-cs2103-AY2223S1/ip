package duke.exceptions;

public class OutOfBoundException extends DukeException {
    private static final String DESCRIPTION = "The provided index number is invalid!";

    public OutOfBoundException() {
        super(DESCRIPTION);
    }
}
