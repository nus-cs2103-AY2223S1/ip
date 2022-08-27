package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;

/**
 * Command for marking Tasks as complete.
 */
public class MarkCommand extends IndexedCommand {
    /**
     * Constructor for MarkCommand.
     *
     * @param index Index of Task.
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * {@inheritDoc}
     * Updates TaskList and Storage and displays mark task message.
     */
    @Override
    public String execute() throws DukeException {
        Task task = taskList.markTask(super.index);
        Command.storage.updateLine(index, task.toStorageFormat());
        return Ui.getMarkTaskMessage(task);
    }
}
