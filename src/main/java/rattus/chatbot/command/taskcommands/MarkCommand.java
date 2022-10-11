package rattus.chatbot.command.taskcommands;

import rattus.chatbot.common.Message;

/**
 * A command that marks the targeted task from the list of Task in the application. The targeted is chosen by an
 * argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class MarkCommand extends TaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected void executeOnTask() {
        targetTask.markDone();
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(Message.MESSAGE_MARKED, targetTask.toString());
        return messageBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof MarkCommand;
    }
}
