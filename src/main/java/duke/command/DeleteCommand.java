package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        Task temp = list.get(index);
        list.remove(index);
        Ui.deleted(temp);
    }
}
