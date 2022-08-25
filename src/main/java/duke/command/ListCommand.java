package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements ICommand {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        Ui.showMsg(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
