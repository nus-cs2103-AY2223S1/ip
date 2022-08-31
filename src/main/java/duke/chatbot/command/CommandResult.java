package duke.chatbot.command;

/**
 * The result of a command being executed. Contains a list
 * of strings to be printed by the UI.
 * @author jq1836
 */
public class CommandResult {
    /** A list of messages to be printed by the UI */
    private final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
