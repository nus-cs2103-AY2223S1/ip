package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private Task task;
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            task = tasks.get(idx);
            tasks.remove(idx);
            ui.deleteTask(tasks, task);
            storage.update(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index.");
        }

    }
}
