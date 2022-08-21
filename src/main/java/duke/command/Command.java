package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList list, FileStorage storage, Ui ui);
}
