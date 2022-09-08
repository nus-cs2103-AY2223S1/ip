package duke.chatbot.command.filtercommands;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_FILTERED_TASKS;

import java.util.function.Predicate;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;

/**
 * Encapsulates a command that filters the application's task list.
 *
 * @author jq1836
 */
public abstract class FilterCommand extends Command {
    private TaskList filteredTasks;

    /**
     * Supplies the condition to test for while filtering.
     *
     * @return A condition to test for while filtering.
     * @throws InvalidInputException If the argument passed to the command is invalid.
     */
    abstract Predicate<Task> supplyCondition() throws InvalidInputException;

    @Override
    protected String buildMessage() {
        if (filteredTasks.isEmpty()) {
            messageBuilder.buildLine(MESSAGE_EMPTY_LIST);
        } else {
            messageBuilder.buildLine(MESSAGE_FILTERED_TASKS);
            messageBuilder.buildLine(filteredTasks.toString());
        }
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        TaskList tasks = duke.getTasks();
        filteredTasks = tasks.filter(supplyCondition());
        return super.execute();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof FilterCommand;
    }
}
