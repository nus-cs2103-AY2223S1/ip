package main.stashy.commands;

import main.stashy.data.exception.StashyException;
import main.stashy.data.task.TaskList;
import main.stashy.storage.Storage;
import main.stashy.ui.Ui;

public abstract class Command {
    public abstract boolean isExit();

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        throw new StashyException("Note to dev: Implement this method in the child class!");
    }
}