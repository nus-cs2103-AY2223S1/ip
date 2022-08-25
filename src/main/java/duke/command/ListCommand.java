package duke.command;

import duke.task.TasksList;

/**
 * Represents a command to add display the current tasks in the TasksList.
 */
public class ListCommand extends Command {
    private TasksList tasksList;

    /**
     * Creates a new ListCommand instance.
     * @param tasksList The TasksList to be displayed.
     */
    public ListCommand(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Returns the string representing the TasksList.
     * @return String representation of the TasksList.
     */
    @Override
    public String execute() {
        return this.tasksList.toString();
    }
}
