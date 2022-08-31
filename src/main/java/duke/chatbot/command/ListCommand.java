package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_LIST;

import duke.chatbot.data.task.Task;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that prints the list of tasks in the application. Prints
 * an empty list message if the list is empty.
 * @author jq1836
 */
public class ListCommand extends Command {
    /**
     * Returns an instance of CommandResult with a message that displays
     * the list of all tasks stored in the application.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();

        if (taskList.isEmpty()) {
            message.addLines(MESSAGE_EMPTY_LIST);
        } else {
            message.addLines(MESSAGE_LIST);
            for (int entry = 1; entry <= taskList.size(); entry++) {
                Task task = taskList.get(entry);
                message.addLines(entry + ". " + task.toString());
            }
        }

        return new CommandResult(message.toString());
    }
}
