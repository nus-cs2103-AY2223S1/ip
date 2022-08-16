public class InvalidValueException extends DukeException {

    private static final String DESCRIPTION = "%s takes in the index of the list as a integer!";

    InvalidValueException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
