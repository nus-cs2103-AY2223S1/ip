package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    int idx;

    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.idx < 0 || this.idx >= tasks.getSize()) {
            throw new DukeException("The index provided is not within the list.");
        };
        String task = tasks.deleteTask(this.idx);
        ui.showDelete(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}