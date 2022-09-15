package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Finds all tasks with the input keyword.
 */
public class Find extends Command {

    /** Keyword to filter the list of tasks */
    private String keyword;

    /**
     * Constructor to initialise the global variables.
     *
     * @param keyword To initialise the keyword to filter the list of tasks.
     */
    public Find(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     *
     * Finds all tasks that contains the keyword.
     * @return A string containing a list of filtered tasks to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        TaskList filteredTasks = (new TaskList(taskList.filterTaskDescription(keyword)));
        return uI.generateTaskListToBePrinted(filteredTasks);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects
     * Returns true if both objects are instance of Find class and find tasks with the same keyword.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Find) {
            Find f = (Find) obj;
            return (f.keyword == this.keyword);
        }
        return false;
    }
}
