package duke.chatbot.command;

import java.util.ArrayList;
import java.util.List;

import static duke.chatbot.common.Message.MESSAGE_UNEXPECTED;

public class InvalidInputCommand extends Command {
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_UNEXPECTED);
        return new CommandResult(message);
    }
}
