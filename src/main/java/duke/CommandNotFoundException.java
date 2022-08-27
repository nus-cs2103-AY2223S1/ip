package duke;

/**
 * Thrown when a given user input for command is not found in the Keyword enum.
 */
public class CommandNotFoundException extends DukeException {

    /**
     * Constructs a new CommandNotFoundException with the command as the error message.
     * @param errorMessage
     */
    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "Command not found: " + super.getMessage();
    }

}
