package justin.command;

import justin.*;
import justin.task.Task;

/**
 * Represents a command to unmark some particular
 * tasks to not be done in the TaskList.
 * @author Justin Cheng.
 */
public class UnmarkCommand extends Command {
    private String[] nums;

    /**
     * Constructor for the UnmarkCommand class.
     * @param nums The array of numbers of targeted tasks
     *            in the TaskList.
     */
    public UnmarkCommand(String... nums) {
        this.nums = nums;
    }

    /**
     * Executes the command by unmarking a task in
     * the TaskList, and saving changes in Storage.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message from the Ui.
     * @throws DukeException If the numbers are invalid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        try {
            assert nums.length <= 30: "Too many tasks to unmark!";
        } catch (AssertionError e) {
            throw new DukeException(e.getMessage());
        }
        String msg = ui.getUnmarkMessage() + ui.getSeparator();
        int count = 1;
        for (String s: nums) {
            try {
                int currNum = Integer.parseInt(s);
                list.unmark(currNum);
                Task task = list.getTask(currNum);
                msg += count + ". " + task + ui.getSeparator();
                count++;
            } catch (NumberFormatException e) {
                throw new DukeException("Please include numbers only or write in a proper format!\n" +
                        "To unmark multiple tasks, add commas between numbers");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task number " + Integer.parseInt(s) + " does not exist!");
            }
        }
        storage.save(list);
        return msg;
    }
}
