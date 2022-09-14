package rattus.chatbot.command;

import rattus.chatbot.data.task.TaskList;
import rattus.chatbot.common.Message;

/**
 * A command that displays the list of tasks in the application. Displays an empty list message if the list is empty.
 *
 * @author jq1836
 */
public class ListCommand extends Command {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "list";

    @Override
    protected String buildMessage() {
        TaskList tasks = duke.getTasks();
        if (tasks.isEmpty()) {
            messageBuilder.buildLines(Message.MESSAGE_EMPTY_LIST);
        } else {
            messageBuilder.buildLines(Message.MESSAGE_LIST);
        }
        messageBuilder.buildLine(tasks.toString());
        return messageBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof ListCommand;
    }
}
