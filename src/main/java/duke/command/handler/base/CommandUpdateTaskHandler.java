package duke.command.handler.base;

import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.tasks.Task;

public abstract class CommandUpdateTaskHandler extends CommandHandler {

    public CommandUpdateTaskHandler(String commandStr, Pattern commandRegexPattern)
            throws CommandException {
        super(commandStr, commandRegexPattern);
    }

    /**
     * Gets the selected task index string in an update command.
     *
     * @return string representation of task index.
     */
    protected abstract String getSelectedTaskIdStr();

    protected abstract CommandResponse updateTask(Task task, int taskIdx, TaskList taskList);

    @Override
    public CommandResponse run(TaskList taskList) throws CommandException {
        String taskIdxStr = getSelectedTaskIdStr();
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx <= 0 || taskIdx > taskList.size()) {
                throw new CommandException("Invalid task selected!");
            }
            return updateTask(taskList.getTask(taskIdx - 1), taskIdx - 1, taskList);
        } catch (NumberFormatException error) {
            throw new CommandException(String.join("\n",
                "Task number should be a number!",
                "Got: %s", taskIdxStr));
        }
    }
}
