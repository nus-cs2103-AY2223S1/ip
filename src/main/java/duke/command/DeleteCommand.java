package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.task.Task;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeOutOfBoundsException(1, tasks.getSize());
        }
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.displayDelete(deletedTask);
    }
}
