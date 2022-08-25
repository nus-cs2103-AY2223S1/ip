package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand implements ICommand {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            Ui.showMsg(taskList.markDone(index));
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand otherCmd = (MarkCommand) obj;
            return this.index == otherCmd.index;
        } else {
            return false;
        }
    }
}
