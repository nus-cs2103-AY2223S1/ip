package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.unmarkTask(targetIndex);
        storage.save(taskList);
    }
}
