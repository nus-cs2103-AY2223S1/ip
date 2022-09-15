package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;

/** Command for updating tasks. */
public class UpdateCommand extends IndexedCommand {
    private final String updatedDescription;

    /**
     * Constructor for UpdateCommand.
     * @param index Index of task to update.
     * @param updatedDescription Updated description.
     */
    public UpdateCommand(int index, String updatedDescription) {
        super(index);
        this.updatedDescription = updatedDescription;
    }

    /**
     * {@inheritDoc}
     * Updates TaskList and Storage and displays mark task message.
     */
    @Override
    public String execute() throws DukeException {
        Task task = taskList.updateTask(super.index, updatedDescription);
        Command.storage.updateLine(index, task.toStorageFormat());
        return Ui.getUpdateTaskMessage(task);
    }

}
