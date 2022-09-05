package duke.exception;

public class InvalidCommandException extends DukeException{
    private static final String errorString = " is an invalid command.";

    public InvalidCommandException(String invalidCommand) {
        super(invalidCommand + errorString);
    }
}
