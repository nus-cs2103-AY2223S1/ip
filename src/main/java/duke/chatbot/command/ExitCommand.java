package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_BYE;

/**
 * A command that prints a farewell message and closes the application.
 *
 * @author jq1836
 */
public class ExitCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns true. Used to detect whether a command results in the closing of the application.
     *
     * @return True
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays a farewell message.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_BYE);
    }
}
