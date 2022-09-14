package duke.commands;

import duke.RecurringTask;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.exceptions.DukeIndexOutOfBoundsException;

/**
 * Represents an executable <code>Command</code> to mark a <code>Task</code> as done.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a <code>MarkCommand</code> command.
     *
     * @param description Input from the user.
     */
    public MarkCommand(String description) {
        super(description);
    }

    /**
     * Marks the user-specified <code>Task</code> as done.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeIndexOutOfBoundsException If user inputted an index outside the range.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeIndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(description.substring(5)) - 1;
            assert index >= 0 : "Task index cannot be less than 0";
            assert index < tasks.getSize() : "Task index cannot be larger than the number of tasks.";
            tasks.markTaskAsDone(index);
            Task task = tasks.get(index);
            String response = "Nice! I've marked this task as done:\n" + task;
            if (task.isRecurring()) {
                RecurringTask recurringTask = (RecurringTask) task;
                tasks.delete(index);
                tasks.add(new RecurringTask(recurringTask));
                response += "\nAnd I've re-added this task for next week!";
            }
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("OOPS!!! You cannot mark a non-existent task as done.");
        }
    }
}
