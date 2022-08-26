package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

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
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.setTaskDoneStatus(index, true);
            storage.writeToFile(taskList);
            ui.sayMarkTask(index, taskList.getTask(index));
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
        if (o instanceof MarkCommand) {
            MarkCommand that = (MarkCommand) o;
            if (index == that.index) {
                return true;
            }
        }
        return false;
    }
}
