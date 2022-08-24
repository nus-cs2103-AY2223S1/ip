package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;
public abstract class Command {

    public abstract void execute(TasksController controller, Ui ui, Storage storage);
}
