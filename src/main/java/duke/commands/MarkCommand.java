package duke.commands;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This class tells Duke to mark the indexed task as done.
 */
public class MarkCommand implements Command {

    /** The index of the task to mark as done. */
    private int index;

    /**
     * Constructs a MarkCommand object.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        taskList.setTaskDoneStatus(index, true);
        storage.writeToFile(taskList);
        return GuiText.formatMarkString(index, taskList.getTask(index));
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof MarkCommand) {
            MarkCommand that = (MarkCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}
