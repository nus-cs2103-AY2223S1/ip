package duke.exceptions;

public class InvalidValueException extends DukeException {

    private static final String DESCRIPTION = "%s takes in the index of the list as a integer!";

    public InvalidValueException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
