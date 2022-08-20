package duke.commands;

public class CommandResult {
    private final String userMessage;
    private final boolean hasUpdates;
    private final boolean isExit;

    public CommandResult(String userMessage, boolean hasUpdates, boolean isExit) {
        this.userMessage = userMessage;
        this.hasUpdates = hasUpdates;
        this.isExit = isExit;
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    public boolean shouldUpdateFile() {
        return this.hasUpdates;
    }

    public boolean shouldExit() {
        return this.isExit;
    }
}
