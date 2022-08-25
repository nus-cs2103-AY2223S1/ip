package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommand deletes a task.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructor for DeleteCommand
     * @param indexToDelete index of task to delete.
     */
    public DeleteCommand(int indexToDelete) {
        super();
        this.indexToDelete = indexToDelete;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(indexToDelete);
        tasks.removeFromTaskList(indexToDelete);
        ui.showDeleteMessage(tasks, taskToDelete);
    }
}
