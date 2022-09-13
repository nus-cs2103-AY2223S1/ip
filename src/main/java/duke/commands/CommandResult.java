package duke.commands;

/**
 * Represents the result of an executed command.
 *
 * @author sikai00
 */
public class CommandResult {
    private final String msg;

    /**
     * Initializes a new CommandResult instance.
     * @param msg Response message from the executed Command.
     */
    public CommandResult(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}
