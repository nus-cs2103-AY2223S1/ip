package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public abstract class Command {

    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FredException;

    public boolean isExit() {
        return isExit;
    }
}
