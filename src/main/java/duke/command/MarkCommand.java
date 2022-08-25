package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {

    private int i;

    public MarkCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (i < 0 || i >= tasks.getSize()) {
            throw new DukeException("OOPS!!! The index is invalid.");
        }
        Task curr = tasks.get(i);
        if (curr.getStatusIcon().equals(" ")) {
            curr.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + curr);
        } else {
            ui.showMessage("This task was already done.");
            ui.showMessage("  " + curr);
        }
    }
}
