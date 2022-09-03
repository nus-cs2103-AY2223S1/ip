package duke.command;

import duke.task.TaskList;

/**
 * Represents a list command
 */
public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers. Example: " + COMMAND_WORD;

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
