package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Lists all tasks in the task list.
 */
public class List extends Command {

    /**
     * {@inheritDoc}
     *
     * Displays the list of tasks in the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) {
        taskList.printTasks(uI);
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
