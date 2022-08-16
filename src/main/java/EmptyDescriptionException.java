public class EmptyDescriptionException extends DukeException {
    private static final String DESCRIPTION = "The description of a %s cannot be empty";

    EmptyDescriptionException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
