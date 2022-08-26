package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command for marking tasks as incomplete.
 */
public class UnmarkCommand extends IndexedCommand {
    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of Task to mark as incomplete.
     */
    public UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Updates TaskList and Storage and displays unmark task message.
     */
    @Override
    public void execute() throws DukeException {
        Task task = taskList.unmarkTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        Command.ui.displayUnmarkTaskMessage(task);
    }
}
