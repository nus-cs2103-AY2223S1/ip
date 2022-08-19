package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

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
