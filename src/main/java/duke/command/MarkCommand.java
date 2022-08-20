package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * A command to mark a task in the task list as done.
 * Usage: mark <index>
 * Note that the index starts at 1.
 */
public class MarkCommand extends Command {
    public final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.showMessage("I've marked this task as done.").showMessage(task.toString());
        storage.save(tasks);
    }
}
