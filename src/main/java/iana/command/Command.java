package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

public abstract class Command {
    
    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
