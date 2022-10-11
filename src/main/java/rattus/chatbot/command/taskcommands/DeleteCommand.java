package rattus.chatbot.command.taskcommands;

import rattus.chatbot.common.Message;

/**
 * A command that deletes the targeted task from the list of Task in the application. The targeted task is chosen by
 * an argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class DeleteCommand extends TaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected void executeOnTask() {
        duke.getTasks().remove(taskIndex);
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(Message.MESSAGE_DELETED, targetTask.toString());
        return messageBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof DeleteCommand;
    }
}
