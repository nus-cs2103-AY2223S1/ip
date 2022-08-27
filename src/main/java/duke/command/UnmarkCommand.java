package duke.command;

import duke.DukeException;
import duke.Ui;
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
     * {@inheritDoc}
     * Updates TaskList and Storage and displays unmark task message.
     */
    @Override
    public String execute() throws DukeException {
        Task task = taskList.unmarkTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        return Ui.displayUnmarkTaskMessage(task);
    }
}
