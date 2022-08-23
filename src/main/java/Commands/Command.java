package Commands;

import Tasks.TaskList;
import Main.Ui;
import Main.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
