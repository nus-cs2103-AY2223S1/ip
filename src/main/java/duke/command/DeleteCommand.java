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
     * @param index Index of Task in TaskList to delete.
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes task, updates Storage and displays delete message.
     */
    @Override
    public void execute() throws DukeException {
        String emptyLine = "";
        Task task = Command.taskList.delete(super.index);
        Command.storage.updateLine(super.index, emptyLine);
        Command.ui.displayDeleteTaskMessage(task, Command.taskList.size());
    }
}
