package duke.chatbot.command;

import java.util.List;

public class CommandResult {
    private final List<String> message;

    public CommandResult(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }
}
