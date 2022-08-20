package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * A command to unmark a task in the task list as done.
 * Usage: unmark <index>
 * Note that the index starts at 1.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = tasks.getTask(index);
        task.markAsUndone();
        ui.showMessage("I've unmarked this task as done.").showMessage(task.toString());
        storage.save(tasks);
    }
}
