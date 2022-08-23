package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
