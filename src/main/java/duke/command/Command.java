package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
