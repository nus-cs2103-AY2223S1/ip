package duke.commands;

import duke.RecurringTask;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>RecurringTask</code>.
 */
public class AddRecurringTaskCommand extends Command {
    /**
     * Constructs a <code>AddRecurringTaskCommand</code> command.
     *
     * @param description Input from the user.
     */
    public AddRecurringTaskCommand(String description) {
        super(description);
    }

    /**
     * Adds a new <code>RecurringTask</code> into the <code>TaskList</code>
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeMissingArgumentException If the input is missing a description or time.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeMissingArgumentException {
        try {
            assert !description.substring(9).isBlank() : "Task description cannot be blank";
            String[] str = description.substring(9).split(" /every ");
            String[] dayAndTime = str[1].split(" ");
            RecurringTask recurringTask = new RecurringTask(str[0], dayAndTime[0], dayAndTime[1], false);
            tasks.add(recurringTask);
            int numberOfTasks = tasks.getSize();
            String response;
            if (numberOfTasks < 2) {
                response = "Got it. I've added this task:\n " + recurringTask
                        + "\nNow you have " + numberOfTasks + " task in the list.";
            } else {
                response = "Got it. I've added this task:\n " + recurringTask
                        + "\nNow you have " + numberOfTasks + " tasks in the list.";
            }
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException("OOPS!!! The description and/or the time of an event "
                    + "cannot be empty.");
        }
    }
}
