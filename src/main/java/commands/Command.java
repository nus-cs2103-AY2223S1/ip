package commands;

import data.*;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    boolean isExit = false;

    public abstract boolean isExit();

    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
