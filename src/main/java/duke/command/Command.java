package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    public abstract boolean isExit();
}
