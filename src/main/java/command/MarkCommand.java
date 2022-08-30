package command;

import exception.DorisException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showMark(tasks.mark(index));
            storage.save(tasks);
        } catch (DorisException e) {
            ui.showError(e);
        }
    }
}