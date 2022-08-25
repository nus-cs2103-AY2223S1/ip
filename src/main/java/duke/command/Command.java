package duke.command;

import duke.model.Task;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    private Boolean isExit = false;
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    public Boolean getIsExit() {
        return this.isExit;
    }

    public void toggleIsExit() {
        this.isExit = !this.isExit;
    }
}
