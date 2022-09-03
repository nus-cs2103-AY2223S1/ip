package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command to unmark a particular
 * task to not be done in the TaskList.
 * @author Justin Cheng.
 */
public class UnmarkCommand extends Command {
    private int num;
    private Task unmarkedTask;

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
    public void execute(TaskList list, Ui ui, Storage storage, MainWindow mw) throws DukeException {
        list.unmark(num);
        this.unmarkedTask = list.getTask(num);
        storage.save(list);
    }

    @Override
    public String getMessage(TaskList list, Ui ui) {
        return ui.unmarkMessage(unmarkedTask);
    }
}
