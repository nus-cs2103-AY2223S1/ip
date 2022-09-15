package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Finds all tasks with the input keyword.
 */
public class FindTag extends Command {

    /** Keyword to filter the list of tasks */
    private String tag;

    /**
     * Constructor to initialise the global variables.
     *
     * @param tag To initialise the tag to filter the list of tasks.
     */
    public FindTag(String tag) {
        this.tag = tag;
    }

    /**
     * {@inheritDoc}
     *
     * Finds all tasks that contains the keyword.
     * @return A string containing a list of filtered tasks to be printed.
     */
    @Override
    public String execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        TaskList filteredTasks = (new TaskList(taskList.filterTaskTag(tag)));
        return uI.generateTaskListToBePrinted(filteredTasks);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects
     * Returns true if both objects are instance of FindTag class and finds tasks with the same tag.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof FindTag) {
            FindTag f = (FindTag) obj;
            return (f.tag == this.tag);
        }
        return false;
    }
}
