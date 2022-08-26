package duke.command;

import java.io.IOException;

import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to mark a task in the task list as done.
 * Usage: mark [0]
 * [0]: index of the task to mark as done
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
