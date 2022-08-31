package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_UNEXPECTED;

/**
 * A command that prints an error message.
 * @author jq1836
 */
public class InvalidInputCommand extends Command {
    /**
     * Returns an instance of CommandResult with a message that displays
     * an error message.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_UNEXPECTED);
    }
}
