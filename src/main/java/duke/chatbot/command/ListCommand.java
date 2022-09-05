package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_LIST;

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
        if (taskList.isEmpty()) {
            messageBuilder.buildLines(MESSAGE_EMPTY_LIST);
        } else {
            messageBuilder.buildLines(MESSAGE_LIST);
        }
        messageBuilder.buildLine(taskList.toString());
        return messageBuilder.toString();
    }
}
