package duke.command;

import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class representing MARK
 */
public class MarkCommand extends CommandWithTasklistAndCommands {
    /**
     * @param arguments The arguments
     */
    public MarkCommand(String[] arguments) {
        super(arguments);
    }
    @Override
    public String execute(Storage storage, TaskList taskList) {
        int index = Integer.parseInt(arguments[0]) - 1;
        taskList.markTaskAsDone(index);
        saveTaskListToStorage(storage, taskList);
        return "Nice! I've marked this task as done:\n    " + taskList.getTaskString(index);
    }
}
