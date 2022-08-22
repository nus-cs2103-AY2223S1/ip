package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class UpdateStatusCommand extends Command {

    private final int index;
    private final boolean isDone;

    public UpdateStatusCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) throws DukeException {
        Task task = tasks.get(index);
        task.setDone(isDone);
        storage.save(tasks);
        ui.print("I've updated the status of this task\n\t" + task);
    }
}
