package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
