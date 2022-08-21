package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
