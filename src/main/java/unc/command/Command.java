package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

abstract public class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws UncException;

    public abstract boolean isExit();
}
