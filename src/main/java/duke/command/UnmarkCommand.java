package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {

    private int i;

    public UnmarkCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (i < 0 || i >= tasks.getSize()) {
            throw new DukeException("OOPS!!! The index is invalid.");
        }
        Task curr = tasks.get(i);
        if (curr.getStatusIcon().equals("X")) {
            curr.unmarkTask();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage("  " + curr);
        } else {
            ui.showMessage("This task has not been done in the first place.");
            ui.showMessage("  " + curr);
        }
    }
}
