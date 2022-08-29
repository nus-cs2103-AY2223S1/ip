package justin.command;

import justin.DukeException;
import justin.Storage;
import justin.task.Task;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command that marks a particular
 * task in the TaskList to be done.
 * @author Justin Cheng.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructor for the MarkCommand class.
     * @param num The position of the task in the
     *            TaskList.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command by marking a task in the
     * TaskList to be done, and saving he changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            list.mark(num);
            Task curr = list.getTask(num);
            ui.markMessage(curr);
            storage.save(list);
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
    }
}
