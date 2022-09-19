package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command that marks some particular
 * tasks in the TaskList to be done.
 * @author Justin Cheng.
 */
public class MarkCommand extends Command {
    private String[] nums;

    /**
     * Constructor for the MarkCommand class.
     * @param nums The array of numbers of the targeted tasks
     *            in the TaskList.
     */
    public MarkCommand(String... nums) {
        this.nums = nums;
    }

    /**
     * Executes the command by marking a task in the
     * TaskList to be done, and saving he changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message from the Ui.
     * @throws DukeException If the numbers are invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        try {
            assert nums.length <= 30: "Too many tasks to mark!";
        } catch (AssertionError e) {
            throw new DukeException(e.getMessage());
        }
        String msg = ui.getMarkMessage() + ui.getSeparator();
        int count = 1;
        for (String s: nums) {
            try {
                int currNum = Integer.parseInt(s);
                list.mark(currNum);
                Task task = list.getTask(currNum);
                msg += count + ". " + task + ui.getSeparator();
                count++;
            } catch (NumberFormatException e) {
                throw new DukeException("Please include numbers only or write in a proper format!\n" +
                        "To mark multiple tasks, add commas between numbers.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task number " + Integer.parseInt(s) + " does not exist!");
            }
        }
        storage.save(list);
        return msg;
    }
}
