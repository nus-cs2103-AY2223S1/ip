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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskToDelete = taskList.getTask(indexToDelete);
        taskList.removeFromTaskList(indexToDelete);
        ui.showDeleteMessage(taskList, taskToDelete);
    }
}
