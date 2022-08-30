package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

public abstract class Command {
    public abstract boolean isExit();

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        throw new StashyException("Note to dev: Implement this method in the child class!");
    }
}