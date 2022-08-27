package duke.commands;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (idx <= 0 || idx > tasks.getSize()) {
            throw new DukeException("Invalid task number, please check your task number again.");
        }
        Task task = tasks.getTask(idx - 1);
        task.markAsDone();
        ui.sendMessage("Marked '" + task.getDescription() + "' as done.");
        tasks.updateStorage();
    }
}
