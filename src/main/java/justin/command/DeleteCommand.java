package justin.command;

import justin.DukeException;
import justin.Storage;
import justin.task.Task;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command to delete a particular
 * task from the TaskList.
 * @author Justin Cheng.
 */
public class DeleteCommand extends Command {
    private int num;

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
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task curr = list.getTask(num);
            list.delete(num);
            ui.deleteMessage(curr);
            storage.save(list);
        } catch (DukeException e){
            ui.showText(e.toString());
        }
    }
}
