package duke.commands;

/**
 * Represents the result of an executed command.
 */
public class CommandResult {
    private final String msg;
    private final boolean shouldExit;

    /**
     * Initializes a new CommandResult instance. Defaults shouldExit to false.
     * @param msg Response message from the executed Command.
     */
    public CommandResult(String msg) {
        this.msg = msg;
        this.shouldExit = false;
    }

    /**
     * Initializes a new CommandResult instance.
     * @param msg Response message from the executed Command.
     * @param shouldExit Whether the Command led to a call for the program to exit
     */
    public CommandResult(String msg, boolean shouldExit) {
        this.msg = msg;
        this.shouldExit = shouldExit;
    }

    public String getMessage() {
        return this.msg;
    }

    public boolean getShouldExit() {
        return this.shouldExit;
    }
}
