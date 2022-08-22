package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class DeleteTaskCommand extends Command {

    private final int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) throws DukeException {
        ui.print("I am removing the following task:\n\t" + tasks.get(index));
        tasks.remove(index);
        storage.save(tasks);
    }
}
