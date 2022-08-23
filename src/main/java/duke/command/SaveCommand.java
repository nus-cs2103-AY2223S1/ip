package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveCommand implements ICommand {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        storage.save(taskList.toSave());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
