package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Lists all tasks in the task list.
 * @return A string containing a list of filtered tasks to be printed.
 */
public class List extends Command {

    /**
     * {@inheritDoc}
     *
     * Displays the list of tasks in the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) {
        return uI.generateTaskListToBePrinted(taskList);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of the List class.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof List) {
            return true;
        }
        return false;
    }
}
