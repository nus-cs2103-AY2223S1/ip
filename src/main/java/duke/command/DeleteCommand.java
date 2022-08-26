package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int ind;

    public DeleteCommand(int ind) {
        this.ind = ind;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.deleteTaskMessage(tasks.delete(ind - 1),tasks);
        storage.save(tasks.toString());
    }
}
