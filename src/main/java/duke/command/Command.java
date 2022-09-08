package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract String execute(TaskList taskList, Storage storage);

}
