package duke.chatbot.command;

import java.util.ArrayList;
import java.util.List;

import static duke.chatbot.common.Message.MESSAGE_BYE;

public class ExitCommand extends Command {
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_BYE);
        return new CommandResult(message);
    }
}
