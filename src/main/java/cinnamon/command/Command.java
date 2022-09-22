package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;
import cinnamon.Handler.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
