package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command to delete a particular
 * task from the TaskList.
 * @author Justin Cheng.
 */
public class DeleteCommand extends Command {
    private String[] nums;
    private Task deletedTask;

    /**
     * Constructor for the DeleteCommand class.
     * @param num The position of the task in the TaskList.
     */
    public DeleteCommand(String... nums) {
        this.nums = nums;
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
        String msg = ui.deleteMessage();
        for (String s: nums) {
            try {
                int num = Integer.parseInt(s);
                Task task = list.getTask(num);
                msg += task + ui.showLine();
            } catch (NumberFormatException e) {
                throw new DukeException("Please include numbers only or write in a proper format!");
            }
        }
        for (String s: nums) {
            list.delete(Integer.parseInt(s));
        }
        storage.save(list);
        return msg + ui.countMessage(list);
    }
}
