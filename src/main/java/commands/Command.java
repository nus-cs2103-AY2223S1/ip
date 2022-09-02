package commands;

import exceptions.DukeException;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {

    public abstract boolean isByeCommand();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
