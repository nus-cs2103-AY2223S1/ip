package rattus.chatbot.command.addcommands;

import rattus.chatbot.command.Command;
import rattus.chatbot.command.CommandResult;
import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.task.Task;
import rattus.chatbot.common.Message;

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
        messageBuilder.buildLines(Message.MESSAGE_ADDED_TASK, task.toString());
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
