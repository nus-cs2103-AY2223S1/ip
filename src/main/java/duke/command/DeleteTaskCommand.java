package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A Command which deletes tasks.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteTaskCommand object which will delete the task at index when executed.
     * The index starts from 0.
     * @param index Index to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at index from the constructor.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskList.isValidIndex(this.index)) {
            ui.showInvalidIndex();
            return;
        }
        ui.showTaskAddedOrDeleted(taskList.deleteTask(index), taskList.getTaskListLength(), false);
    }

    /**
     * Whether the DeleteTaskCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
