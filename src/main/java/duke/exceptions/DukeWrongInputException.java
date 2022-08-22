package duke.exceptions;

public class DukeWrongInputException extends DukeException {
    private static final String MESSAGE = "The additional argument for %s is of the wrong data type.\n";

    public DukeWrongInputException(String command) {
        super(String.format(MESSAGE, command));
    }
}
