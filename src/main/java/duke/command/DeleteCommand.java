package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    DeleteCommand(int index) {
        super(index);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index, ui);
        storage.saveToFile(tasks);
    }
}
