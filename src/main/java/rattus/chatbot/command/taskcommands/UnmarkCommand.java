package rattus.chatbot.command.taskcommands;

import static rattus.chatbot.common.Message.MESSAGE_UNMARKED;

/**
 * A command that unmarks the targeted task from the list of Task in the application. The targeted is chosen by an
 * argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class UnmarkCommand extends TaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected void executeOnTask() {
        targetTask.markUndone();
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_UNMARKED, targetTask.toString());
        return messageBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof UnmarkCommand;
    }
}
