package sus.commands;

import sus.SusException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.Task;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Deadline.\n"
            + "\tEx: " + COMMAND_WORD + " <description> /by <date/time>";

    private final String description;
    private final String dueDate;

    /**
     * Constructor.
     *
     * @param description description of the new deadline
     * @param dueDate date the deadline is to be completed by
     */
    public DeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.addDeadline(description, dueDate);
            storage.save(taskList);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED + "\n"
                    + task + "\n"
                    + String.format(Messages.MESSAGE_TASK_NUMBER, taskList.getNumberOfTasks()));
        } catch (SusException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
