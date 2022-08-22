package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command for marking Tasks as complete.
 */
public class MarkCommand extends IndexedCommand {
    /**
     * Constructor for MarkCommand.
     *
     * @param index index of Task .
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Updates TaskList and Storage and displays mark task message.
     */
    @Override
    public void execute() throws DukeException {
        Task task = taskList.markTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        Command.ui.displayMarkTaskMessage(task);
    }
}
