package command;

import storage.Storage;

import exception.DorisException;

import tasklist.*;

import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deleted = tasks.delete(this.index);
            ui.showDeleted(tasks, deleted);
            storage.save(tasks);
        } catch (DorisException e) {
            ui.showError(e);
        }
    }
}
