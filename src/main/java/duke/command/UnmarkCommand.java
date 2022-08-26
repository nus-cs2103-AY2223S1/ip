package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is the command to mark a task as done.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param num Number of the task to be marked as undone.
     */
    public UnmarkCommand(int num) {
        super();
        this.num = num;
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     */
    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.getTask(this.num).markAsUndone();
        ui.showUnmark(list.getTask(this.num));
        storage.saveList(list.save());
    }
}
