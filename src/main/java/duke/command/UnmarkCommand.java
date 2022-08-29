package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeOutOfBoundsException;
import duke.task.Task;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeOutOfBoundsException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeOutOfBoundsException(1, tasks.getSize());
        }
        Task unmarkedTask = tasks.unmarkTask(index);
        storage.save(tasks);
        ui.displayMark(unmarkedTask);
    }


}
