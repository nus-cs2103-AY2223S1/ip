public class InvalidTimeFormatException extends DukeException {

    private static final String DESCRIPTION = "%s is not recognised as a time stamp";

    InvalidTimeFormatException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
