package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;

import java.util.ArrayList;
import java.util.List;

public class InvalidInputCommand extends Command {
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MessageConstants.MESSAGE_UNEXPECTED);
        return new CommandResult(message);
    }
}
