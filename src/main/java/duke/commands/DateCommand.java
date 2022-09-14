package duke.commands;

import duke.exceptions.ParseInputException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a command that finds tasks
 * whose date information matches the given date.
 */
public class DateCommand extends Command {

    public static final String COMMAND_WORD = "date";

    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n";

    private String targetDate;

    /**
     * Constructs a DateCommand instance using a target date in string.
     *
     * @param targetDate Target date in string.
     */
    public DateCommand(String targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Executes the command by listing all tasks
     * whose date information matches the given one.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            TaskList newTaskList = tasks.findByDate(targetDate);
            String successMessage = MESSAGE_SUCCESS + newTaskList.iterate();
            return successMessage;
        } catch (ParseInputException e) {
            return e.getMessage();
        } catch (NullPointerException npe) {
            return "☹ Ah need to remind you again. Enter a date!";
        }
    }

}
