package duke.command;

import java.io.IOException;

import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to delete a task from the task list.
 * Usage: delete [0]
 * [0]: index of the task to delete
 * Note that the index starts at 1.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showMessage("I've deleted this task.")
                .showMessage(task.toString())
                .showTaskListSize(tasks);
        storage.save(tasks);
    }
}
