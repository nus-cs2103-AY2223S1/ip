package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A Command which marks a task as either done or not done.
 */
public class MarkTaskCommand extends Command {
    private boolean isDone;
    private int index;

    /**
     * Constructs a MarkTaskCommand which would mark the task at index as isDone when executed.
     * The index starts at 0.
     * @param isDone Whether to mark the task as done or not.
     * @param index Index of task to mark.
     */
    public MarkTaskCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Marks the task at index specified in constructor of the TaskList object as isDone which is also specified in
     * constructor.
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
        if (this.isDone == taskList.getIsDone(index)) {
            ui.showAlreadyMarked(isDone);
            return;
        }
        assert taskList.isValidIndex(this.index);
        ui.showStatusChange(taskList.changeStatus(index, this.isDone), this.isDone);
    }

    /**
     * Whether the MarkTaskCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
