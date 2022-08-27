package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    public AddCommand(Task task) {
        super(task);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        storage.saveToFile(tasks);
    }
}
