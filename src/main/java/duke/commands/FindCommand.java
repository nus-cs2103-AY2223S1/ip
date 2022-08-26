package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class tells Duke to find all tasks containing the search string.
 */
public class FindCommand implements Command {

    /** The string to search for in the task's description. */
    private String searchString;

    /**
     * Constructs a FindCommand object.
     *
     * @param searchString The string to search for in the task's description.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the find command from the user.
     *
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.sayFind();
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i).getDescription().contains(searchString)) {
                    ui.sayTaskWithIndex(i, taskList.getTask(i));
                }
            }
            ui.sayFinishListing();
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand that = (FindCommand) o;
            if (searchString.equals(that.searchString)) {
                return true;
            }
        }
        return false;
    }
}
