package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_LIST;

import duke.chatbot.data.task.Task;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that displays the list of tasks in the application. Displays an empty list message if the list is empty.
 *
 * @author jq1836
 */
public class ListCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Returns an instance of {@link CommandResult} with a message that displays the list of all tasks stored in the
     * application.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        if (taskList.isEmpty()) {
            message.buildLines(MESSAGE_EMPTY_LIST);
        } else {
            message.buildLines(MESSAGE_LIST);
        }
        message.buildLine(taskList.toString());
        return new CommandResult(message.toString());
    }
}
