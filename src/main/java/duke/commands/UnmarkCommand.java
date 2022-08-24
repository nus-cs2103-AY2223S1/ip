package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class UnmarkCommand implements Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.setTaskDoneStatus(index, false);
            storage.writeToFile(taskList);
            ui.unmarkTask(index, taskList.getTask(index));
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnmarkCommand) {
            UnmarkCommand that = (UnmarkCommand) o;
            if (this.index == that.index) {
                return true;
            }
        }
        return false;
    }
}
