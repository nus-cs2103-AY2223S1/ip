package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command that marks a particular
 * task in the TaskList to be done.
 * @author Justin Cheng.
 */
public class MarkCommand extends Command {
    private int num;
    private Task markedTask;

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
     * @return The String message from the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        list.mark(num);
        this.markedTask = list.getTask(num);
        storage.save(list);
        return ui.markMessage(markedTask);
    }
}
