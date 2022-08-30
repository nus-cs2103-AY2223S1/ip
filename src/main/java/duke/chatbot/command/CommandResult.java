package duke.chatbot.command;

import java.util.List;

/**
 * The result of a command being executed. Contains a list
 * of strings to be printed by the UI.
 * @author Jordan Quah Shao Xuan
 */
public class CommandResult {
    /** A list of messages to be printed by the UI */
    private final List<String> message;

    public CommandResult(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }
}
