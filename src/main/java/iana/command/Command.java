package iana.command;

import iana.main.Storage;
import iana.main.TaskList;
import iana.main.Ui;

public abstract class Command {
    
    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
