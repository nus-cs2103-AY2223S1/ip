package duke.commands;

import duke.gui.GuiText;
import duke.tools.SessionManager;
import duke.tools.TaskList;

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
     * @return The string to be shown by Duke on the dialogue box.
     */
    @Override
    public String execute() {
        TaskList taskList = SessionManager.getTaskList();
        return GuiText.formatFindString(taskList, searchString);
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
