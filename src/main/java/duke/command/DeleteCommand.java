package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        super();
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskToDelete = taskList.getTask(indexToDelete);
        taskList.removeFromTaskList(indexToDelete);
        ui.showDeleteMessage(taskList, taskToDelete);
    }
}
