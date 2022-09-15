package duke.commands;

import duke.RecurringTask;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeMissingArgumentException;

/**
 * Represents an executable <code>Command</code> to add <code>RecurringTask</code>.
 */
public class AddRecurringTaskCommand extends Command {

    private static final int DESCRIPTION_INDEX = 9;

    private static final String MESSAGE_ARGUMENT_MISSING = "OOPS!!! The description and/or the time of a recurring "
            + "task cannot be empty.";
    private static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\nNow you have %d tasks "
            + "in the list.";
    private static final String REGEX_KEYWORD_DAY = " /every ";
    private static final String REGEX_KEYWORD_TIME = " /at ";

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
            assert !description.substring(DESCRIPTION_INDEX).isBlank() : MESSAGE_ARGUMENT_MISSING;
            String[] str = description.substring(DESCRIPTION_INDEX).split(REGEX_KEYWORD_DAY);
            String[] dayAndTime = str[1].split(REGEX_KEYWORD_TIME);
            RecurringTask recurringTask = new RecurringTask(str[0], dayAndTime[0], dayAndTime[1], false);
            tasks.add(recurringTask);
            int numberOfTasks = tasks.getSize();
            String response = String.format(MESSAGE_SUCCESS, recurringTask, numberOfTasks);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException(MESSAGE_ARGUMENT_MISSING);
        }
    }
}
