package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_BYE;

/**
 * A command that prints a farewell message and closes
 * the application.
 * @author jq1836
 */
public class ExitCommand extends Command {
    /**
     * Returns an instance of CommandResult with a message that displays
     * a farewell message.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_BYE);
    }
}
