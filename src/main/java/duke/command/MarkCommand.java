package duke.command;

import duke.TaskList;

/**
 * Concrete class representing MARK
 */
public class MarkCommand extends CommandWithTasklistAndCommands {
    /**
     * @param taskList The tasks list
     * @param arguments The arguments
     */
    public MarkCommand(TaskList taskList, String[] arguments) {
        super(taskList, arguments);
    }
    @Override
    public String execute() {
        int index = Integer.parseInt(arguments[0]) - 1;
        taskList.markTaskAsDone(index);
        return "Nice! I've marked this task as done:\n    " + taskList.getTaskString(index);
    }
}
