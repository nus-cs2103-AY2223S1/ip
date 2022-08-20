package duke.command;

import duke.FileStorage;
import duke.task.TaskList;
import duke.Ui;
public abstract class Command {

    public abstract void execute(TaskList list, FileStorage storage, Ui ui);
}
