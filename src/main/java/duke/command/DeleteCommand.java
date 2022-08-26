package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            tasks.deleteTask(taskToDelete);
            storage.store(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index not found");
        }

    }
}
