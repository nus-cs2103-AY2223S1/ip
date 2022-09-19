package justin.command;

import justin.*;
import justin.task.Task;

import java.util.Arrays;
import java.util.Collections;

/**
 * Represents a command to delete a particular
 * task from the TaskList.
 * @author Justin Cheng.
 */
public class DeleteCommand extends Command {
    private String[] nums;
    private Integer[] nos;

    /**
     * Constructor for the DeleteCommand class.
     * @param nums The array of numbers of the tasks
     *            to be deleted in the TaskList.
     */
    public DeleteCommand(String... nums) {
        this.nums = nums;
        this.nos = new Integer[nums.length];
    }

    /**
     * Executes the command which involves deleting a task,
     * and saving the changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message of the Ui.
     * @throws DukeException If number format is wrong
     * or index is out of bounds of the TaskList.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String msg = ui.getDeleteMessage() + ui.getSeparator();
        for (String s: nums) {
            try {
                int num = Integer.parseInt(s);
                Task task = list.getTask(num);
                msg += task + ui.getSeparator();
            } catch (NumberFormatException e) {
                throw new DukeException("Please include numbers only or write in a proper format!\n" +
                        "To delete multiple tasks, add commas between numbers.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task number " + Integer.parseInt(s) + " does not exist!");
            }
        }
        for (int i = 0; i < nos.length; i++) {
            nos[i] = Integer.parseInt(nums[i]);
        }
        Arrays.sort(nos, Collections.reverseOrder()); //reverse order because need to delete the largest number first
        for (int no: nos) {
            list.delete(no);
        }
        storage.save(list);
        return msg + ui.getCountMessage(list);
    }
}
