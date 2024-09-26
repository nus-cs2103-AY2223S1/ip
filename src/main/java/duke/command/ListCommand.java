package duke.command;

import duke.component.TaskList;

/**
 * Represents a command to list all tasks in the TaskList.
 */
public class ListCommand extends Command {

    public static final String MSG_LIST = "Here are the tasks in your list:\n";
    public static final String MSG_NO_TASKS = "There are no outstanding tasks in your list!\n";

    /**
     * Constructs a new ListCommand.
     *
     * @param tasks List of all tasks.
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return String representation of the message in response to the command.
     */
    @Override
    public String run() {
        if (tasks.toString().equals("")) {
            return MSG_NO_TASKS;
        }
        return MSG_LIST + tasks.toString();
    }

}
