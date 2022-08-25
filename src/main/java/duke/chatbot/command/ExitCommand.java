package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that prints a farewell message and closes
 * the application.
 */
public class ExitCommand extends Command {
    /**
     * Returns an instance of CommandResult with a message that displays
     * a farewell message.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MessageConstants.MESSAGE_BYE);
        return new CommandResult(message);
    }
}
