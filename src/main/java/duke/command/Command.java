package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
