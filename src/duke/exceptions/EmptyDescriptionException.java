package duke.exceptions;

public class EmptyDescriptionException extends DukeException {
    private static final String DESCRIPTION = "The description of a %s cannot be empty";

    public EmptyDescriptionException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
