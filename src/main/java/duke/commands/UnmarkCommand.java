package duke.commands;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This class tells Duke to mark the indexed task as not done.
 */
public class UnmarkCommand implements Command {

    /** The index of the task to mark as not done. */
    private int index;

    /**
     * Constructs a UnmarkCommand object.
     *
     * @param index The index of the task to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        taskList.setTaskDoneStatus(index, false);
        storage.writeToFile(taskList);
        return GuiText.formatUnmarkString(index, taskList.getTask(index));
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof UnmarkCommand) {
            UnmarkCommand that = (UnmarkCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}
