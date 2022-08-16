package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.getSize()) {
            throw new DukeException("The index provided is not within the list.");
        }
        ui.showUnmark(tasks.unmarkTask(idx));
        storage.save(tasks.saveTasks());
    }
}
