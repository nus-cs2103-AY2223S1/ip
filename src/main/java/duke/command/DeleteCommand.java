package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task tmp = tasks.delete(taskNo);
            storage.write(tasks.toStringWritable());
            ui.showOutput("Noted. I've removed this task:");
            ui.showOutput("\t" + tmp.toString());
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("Invalid task to delete.");
        }
    }
    public boolean isExit() {
        return false;
    }
}
