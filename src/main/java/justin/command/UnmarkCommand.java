package justin.command;

import justin.DukeException;
import justin.Storage;
import justin.task.Task;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command to unmark a particular
 * task to not be done in the TaskList.
 * @author Justin Cheng.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructor for the UnmarkCommand class.
     * @param num The position of the task in the
     *            TaskList.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command by unmarking a task in
     * the TaskList, and saving changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            list.unmark(num);
            Task curr = list.getTask(num);
            ui.unmarkMessage(curr);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}
