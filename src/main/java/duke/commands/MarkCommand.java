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

    private static final int DESCRIPTION_INDEX = 5;

    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "OOPS!!! You cannot mark a non-existent task as done.";
    private static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n %s";
    private static final String MESSAGE_SUCCESS_RECURRING_TASK = "\nAnd I've re-added this task for next week!";

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
            int index = changeToZeroIndex(Integer.parseInt(description.substring(DESCRIPTION_INDEX)));
            assert index >= 0 : MESSAGE_INDEX_OUT_OF_BOUNDS;
            assert index < tasks.getSize() : MESSAGE_INDEX_OUT_OF_BOUNDS;
            tasks.markTaskAsDone(index);
            Task task = tasks.get(index);
            String response = String.format(MESSAGE_SUCCESS, task);
            if (task.isRecurring()) {
                RecurringTask recurringTask = (RecurringTask) task;
                tasks.delete(index);
                tasks.add(new RecurringTask(recurringTask));
                response += MESSAGE_SUCCESS_RECURRING_TASK;
            }
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
    }

    private int changeToZeroIndex(int index) {
        return index - 1;
    }
}
