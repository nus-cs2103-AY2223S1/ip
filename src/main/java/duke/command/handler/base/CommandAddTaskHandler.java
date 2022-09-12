package duke.command.handler.base;

import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.AddTaskResponse;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.tasks.Task;

public abstract class CommandAddTaskHandler extends CommandHandler {

    public CommandAddTaskHandler(String commandStr, Pattern commandRegexPattern)
            throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    protected abstract Task getTaskFromCommand() throws CommandException;

    @Override
    protected abstract String getInvalidFormatMessage();

    /**
     * Adds a task to the task list.
     *
     * @param taskList task list.
     * @return add task response.
     * @throws CommandException if there are errors when creating a task.
     */
    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        Task newTask = getTaskFromCommand();
        taskList.addTask(newTask);

        return new AddTaskResponse(newTask, taskList.size());
    }
}
