package duke.chatbot.command.TargetTaskCommands;

import static duke.chatbot.common.Message.MESSAGE_DELETED;

import duke.chatbot.util.MessageBuilder;

/**
 * A command that deletes the targeted task from the list of Task in the application. The targeted task is chosen by
 * an argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class DeleteCommand extends TargetTaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected void executeOnTask() {
        taskList.remove(taskIndex);
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_DELETED, targetTask.toString());
        return messageBuilder.toString();
    }
}
