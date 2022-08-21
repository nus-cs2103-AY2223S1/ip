package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task in the task list as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n"
            + "\tEx.: " + COMMAND_WORD + " <number>";

    private final int targetIndex;

    /**
     * Constructor for MarkCommand.
     *
     * @param targetIndex index of the task to be marked
     */
    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.markTask(targetIndex);
        storage.save(taskList);
    }
}
