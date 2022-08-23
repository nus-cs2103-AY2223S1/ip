package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;

import java.util.ArrayList;
import java.util.List;

public class ExitCommand extends Command {
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MessageConstants.MESSAGE_BYE);
        return new CommandResult(message);
    }
}
