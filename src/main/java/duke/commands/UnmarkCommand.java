package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

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
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.setTaskDoneStatus(index, false);
            storage.writeToFile(taskList);
            ui.sayUnmarkTask(index, taskList.getTask(index));
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
        if (o instanceof UnmarkCommand) {
            UnmarkCommand that = (UnmarkCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}
