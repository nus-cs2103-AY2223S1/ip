package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements ICommand {
    private final String searchStr;

    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        Ui.showMsg(taskList.find(this.searchStr));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
