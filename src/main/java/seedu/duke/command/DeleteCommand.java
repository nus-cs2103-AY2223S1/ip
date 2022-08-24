package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    protected int indexOfTask;
    public DeleteCommand(int indexOfTask) {
        super(false);
        this.indexOfTask = indexOfTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(indexOfTask);
        ui.deleteTask(task);
        ui.displayNumberOfTasks(tasks.getNumberOfTasks());
        storage.writeToFile(tasks);
    }
}
