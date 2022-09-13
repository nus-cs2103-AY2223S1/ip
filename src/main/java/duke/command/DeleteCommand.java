package duke.command;

import duke.Responses;
import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class representing DELETE
 */
public class DeleteCommand extends CommandWithTasklistAndCommands {
    /**
     * @param arguments The arguments
     */
    public DeleteCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        int index = Integer.parseInt(arguments[0]) - 1;
        String deletedTaskDescription = taskList.getTaskString(index);
        taskList.removeTask(index);
        saveTaskListToStorage(storage, taskList);
        return "Noted. I've removed this task:\n    "
                + deletedTaskDescription + "\n" + Responses.generateTasksNumberMessage(taskList);
    }
}

