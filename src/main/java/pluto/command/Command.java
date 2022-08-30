package pluto.command;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException;
}
