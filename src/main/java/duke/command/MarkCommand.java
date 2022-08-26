package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructor for MarkCommand.
     *
     * @param num Number of the task to be marked as done.
     */
    public MarkCommand(int num) {
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
        list.getTask(this.num).markAsDone();
        ui.showMark(list.getTask(this.num));
        storage.saveList(list.save());
    }
}
