package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int number;

    public DeleteCommand(int number) {
        this.number = number;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskList().get(this.number - 1);
        tasks.delete(this.number - 1);
        int total = tasks.totalSize();
        ui.showDelete(task,total);
        storage.store(tasks.getTaskList());
    }
}
