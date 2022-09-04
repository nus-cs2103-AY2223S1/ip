package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command to delete a particular
 * task from the TaskList.
 * @author Justin Cheng.
 */
public class DeleteCommand extends Command {
    private int num;
    private Task deletedTask;

    /**
     * Constructor for the DeleteCommand class.
     * @param num The position of the task in the TaskList.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command which involves deleting a task,
     * and saving the changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message of the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        this.deletedTask = list.getTask(num);
        list.delete(num);
        storage.save(list);
        return ui.deleteMessage(deletedTask) + ui.showLine() + ui.countMessage(list);
    }
}
