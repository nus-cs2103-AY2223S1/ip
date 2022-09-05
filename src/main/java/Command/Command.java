package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public boolean isExit;

    public Command() {}

    public abstract String execute(TaskList taskList, Storage storage, Ui ui);

}
