public class CommandResult {
    private final String userMessage;
    private final boolean hasUpdates;

    public CommandResult(String userMessage, boolean hasUpdates) {
        this.userMessage = userMessage;
        this.hasUpdates = hasUpdates;
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    public boolean shouldUpdateFile() {
        return this.hasUpdates;
    }
}
