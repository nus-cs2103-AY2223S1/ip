package rattus.chatbot.command;

import rattus.chatbot.common.Message;

/**
 * A command that displays a farewell message and closes the application.
 *
 * @author jq1836
 */
public class ExitCommand extends Command {
    /**
     * The command word to invoke this command.
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

    @Override
    protected String buildMessage() {
        return Message.MESSAGE_BYE;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof ExitCommand;
    }
}
