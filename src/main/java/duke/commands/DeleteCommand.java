package duke.commands;

import duke.exceptions.DukeException;
import duke.gui.GuiText;
import duke.tasks.Task;
import duke.tools.SessionManager;
import duke.tools.Storage;
import duke.tools.TaskList;

/**
 * This class tells Duke to delete a specific task from the stored tasks.
 */
public class DeleteCommand implements Command {

    /** The index of the task to delete. */
    private int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    @Override
    public String execute() throws DukeException {
        TaskList taskList = SessionManager.getTaskList();
        Storage storage = SessionManager.getStorage();
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.writeToFile(taskList);
        return GuiText.formatDeleteString(index, task);
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteCommand) {
            DeleteCommand that = (DeleteCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}
