package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
