package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        if (index > list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        boolean success = list.get(index).markUndone();
        Ui.unmarked(list, index, success);
    }
}
