package duke.commands;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Deadline.\n"
            + "\tEx.: " + COMMAND_WORD + " <description> /by <date/time>";

    private final String description;
    private final String by;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description description of the new deadline
     * @param by date the deadline is to be completed by
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.addDeadline(description, by);
            storage.save(taskList);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED + "\n"
                    + task + "\n"
                    + String.format(Messages.MESSAGE_TASK_NUMBER, taskList.getNumberOfTasks()));
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
