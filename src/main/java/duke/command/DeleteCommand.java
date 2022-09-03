package duke.command;

import duke.DukeException;
import duke.Ui;
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
     * {@inheritDoc}
     * Deletes task, updates Storage and displays delete message.
     */
    @Override
    public String execute() throws DukeException {
        int initialTaskListSize = Command.taskList.size();

        Task task = Command.taskList.delete(super.index);
        Command.storage.deleteLine(super.index);

        int finalTaskListSize = Command.taskList.size();
        assert finalTaskListSize - initialTaskListSize == 1;

        return Ui.getDeleteTaskMessage(task, Command.taskList.size());
    }
}
