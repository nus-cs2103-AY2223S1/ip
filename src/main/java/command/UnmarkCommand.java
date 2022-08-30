package command;

import exception.DorisException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showMark(tasks.unmark(index));
            storage.save(tasks);
        } catch (DorisException e) {
            ui.showError(e);
        }
    }
}