package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command for deleting Tasks.
 */
public class DeleteCommand extends IndexedCommand {
    /**
     * Constructor for DeleteCommand.
     *
     * @param index index of Task in TaskList to delete.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes task, updates Storage and displays delete message.
     */
    @Override
    public void execute() throws DukeException {
        Task task = Command.taskList.delete(super.index);
        Command.storage.save(task);
        Command.ui.displayDeleteTaskMessage(task, Command.taskList.size());
    }
}
