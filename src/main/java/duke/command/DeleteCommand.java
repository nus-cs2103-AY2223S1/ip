package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(this.num);
        tasks.deleteTask(this.num);
        ui.printDeleteTask(task, tasks);
        storage.writeFile(tasks);
    }
}
