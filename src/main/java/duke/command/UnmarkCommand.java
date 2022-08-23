package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand implements ICommand {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            Ui.showMsg(taskList.unmarkDone(index));
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
