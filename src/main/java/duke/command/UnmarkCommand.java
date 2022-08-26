package duke.command;

import java.util.ArrayList;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = taskList.getTaskArrayList();
        if ((index > 0) && index < list.size() && (list.get(index - 1) != null)) {
            Task t = list.get(index - 1);
            taskList.unmark(this.index);
            ui.showUnmark(t);
        } else {
            String s = "☹ OOPS!!! The index of the task to be marked/unmarked/deleted must be valid/within range.";
            throw new DukeException(s);
        }
    }
}
