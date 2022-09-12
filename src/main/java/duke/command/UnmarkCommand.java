package duke.command;

import duke.TaskList;

/**
 * Concrete class representing UNMARK
 */
public class UnmarkCommand extends CommandWithTasklistAndCommands {
    /**
     * Constructs an instance of UnmarkCommand
     * @param taskList The tasks list
     * @param arguments The arguments
     */
    public UnmarkCommand(TaskList taskList, String[] arguments) {
        super(taskList, arguments);
    }
    @Override
    public String execute() {
        int index = Integer.parseInt(arguments[0]) - 1;
        taskList.markTaskAsNotDone(index);
        return "OK, I've marked this task as not done yet:\n    "
                + taskList.getTaskString(index);
    }
}
