package duke.commands;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Unmark a task in the task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark a task (not done).\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int targetIndex;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param targetIndex index of the task to be unmarked
     */
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.unmarkTask(targetIndex);
            storage.save(taskList);
            return new CommandResult(String.format(Messages.MESSAGE_TASK_UPDATE_STATUS + "\n%s", "not done", task));
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
