package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
