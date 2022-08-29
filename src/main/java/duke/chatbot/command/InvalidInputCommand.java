package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_UNEXPECTED;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that prints an error message.
 */
public class InvalidInputCommand extends Command {
    /**
     * Returns an instance of CommandResult with a message that displays
     * an error message.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_UNEXPECTED);
        return new CommandResult(message);
    }
}
