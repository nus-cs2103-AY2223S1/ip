package duke.command;

import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.task);
        ui.printAddTasks(this.task, tasks);
        storage.writeFile(tasks);
    }
}
