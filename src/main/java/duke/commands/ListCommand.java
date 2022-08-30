package duke.commands;

import duke.gui.GuiText;
import duke.tools.SessionManager;
import duke.tools.TaskList;

/**
 * This class performs the required actions for Duke to list out all the tasks stored.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     */
    @Override
    public String execute() {
        TaskList taskList = SessionManager.getTaskList();
        return GuiText.formatListString(taskList);
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
