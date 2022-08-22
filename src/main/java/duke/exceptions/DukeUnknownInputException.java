package duke.exceptions;

public class DukeUnknownInputException extends DukeException {
    private static final String MESSAGE = "I'm sorry, I don't understand the command %s :(\n";

    public DukeUnknownInputException(String command) {
        super(String.format(MESSAGE, command));
    }
}
