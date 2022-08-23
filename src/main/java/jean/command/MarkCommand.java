package jean.command;

import jean.storage.Storage;
import jean.task.Task;
import jean.task.TaskList;
import jean.ui.Ui;

public class MarkCommand extends Command {
    int taskIndex;
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.taskIndex, ui);
    }
}
