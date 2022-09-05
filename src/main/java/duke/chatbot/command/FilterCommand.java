package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_FILTERED_TASKS;

import java.util.function.Predicate;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * Encapsulates a command that filters the application's task list.
 *
 * @author jq1836
 */
public abstract class FilterCommand extends Command {
    /**
     * Supplies the condition to test for while filtering.
     *
     * @return A condition to test for while filtering.
     * @throws InvalidInputException If the argument passed to the command is invalid.
     */
    abstract Predicate<Task> getCondition() throws InvalidInputException;

    /**
     * Builds the message for the filtered list of tasks.
     *
     * @param tasks The filtered list of tasks.
     * @return A string that represents the message.
     */
    private String buildMessage(TaskList tasks) {
        MessageBuilder messageBuilder = new MessageBuilder();
        if (tasks.isEmpty()) {
            messageBuilder.buildLine(MESSAGE_EMPTY_LIST);
        } else {
            messageBuilder.buildLine(MESSAGE_FILTERED_TASKS);
            messageBuilder.buildLine(tasks.toString());
        }
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        assert(taskList != null);
        TaskList filteredTasks = taskList.filter(getCondition());
        String message = buildMessage(filteredTasks);
        return new CommandResult(message);
    }
}
