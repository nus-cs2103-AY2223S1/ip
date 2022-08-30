package duke.commands;

public class CommandResult {
    private final String msg;
    private final boolean shouldExit;

    public CommandResult(String msg) {
        this.msg = msg;
        this.shouldExit = false;
    }

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
