package duke.command;

import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class representing UNMARK
 */
public class UnmarkCommand extends CommandWithTasklistAndCommands {
    /**
     * Constructs an instance of UnmarkCommand
     * @param arguments The arguments
     */
    public UnmarkCommand(String[] arguments) {
        super(arguments);
    }
    @Override
    public String execute(Storage storage, TaskList taskList) {
        int index = Integer.parseInt(arguments[0]) - 1;
        taskList.markTaskAsNotDone(index);
        saveTaskListToStorage(storage, taskList);
        return "OK, I've marked this task as not done yet:\n    "
                + taskList.getTaskString(index);
    }
}
