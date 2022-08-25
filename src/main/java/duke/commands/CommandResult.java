package duke.commands;

public class CommandResult {
    private final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    /**
     * The getMessage function returns the message that was passed into the
     * constructor.
     * 
     * @return The message variable
     */
    public String getMessage() {
        return message;
    }
}
