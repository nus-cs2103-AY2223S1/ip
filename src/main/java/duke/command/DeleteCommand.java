package duke.command;

import duke.Responses;
import duke.TaskList;

/**
 * Concrete class representing DELETE
 */
public class DeleteCommand extends CommandWithTasklistAndCommands {
    /**
     * @param taskList  The tasks list
     * @param arguments The arguments
     */
    public DeleteCommand(TaskList taskList, String[] arguments) {
        super(taskList, arguments);
    }

    @Override
    public String execute() {
        int index = Integer.parseInt(arguments[0]) - 1;
        String deletedTaskDescription = taskList.getTaskString(index);
        taskList.removeTask(index);
        return "Noted. I've removed this task:\n    "
                + deletedTaskDescription + "\n" + Responses.generateTasksNumberMessage(taskList);
    }
}

