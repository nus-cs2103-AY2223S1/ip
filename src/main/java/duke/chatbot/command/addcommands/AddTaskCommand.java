package duke.chatbot.command.addcommands;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

import duke.chatbot.command.Command;
import duke.chatbot.command.CommandResult;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;

/**
 * Encapsulates a {@link Command} that adds a task to the application's task list.
 *
 * @author jq1836
 */
public abstract class AddTaskCommand extends Command {
    private Task task;

    /**
     * Supplies the instance of {@link Task} to add to the list.
     *
     * @return The task to add to the list.
     */
    protected abstract Task supplyTask() throws InvalidInputException;

    /**
     * Adds an instance of {@link Task} to the list.
     */
    private void addTask() {
        duke.getTasks().add(task);
    }

    @Override
    protected String buildMessage() {
        messageBuilder.buildLines(MESSAGE_ADDED_TASK, task.toString());
        return messageBuilder.toString();
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        task = supplyTask();
        addTask();
        return super.execute();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof AddTaskCommand;
    }
}
