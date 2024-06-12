package ado.command;

import ado.AdoException;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Defines general characteristics of Command classes.
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException;

    public abstract boolean isExit();
}
