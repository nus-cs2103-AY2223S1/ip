package uwu.command;

import uwu.exception.UwuException;

import uwu.Storage;

import uwu.task.TaskList;

import uwu.Ui;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException;
    public abstract boolean isExit();
}
