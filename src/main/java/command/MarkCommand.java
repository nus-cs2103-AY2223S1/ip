package command;

import exception.DorisException;

import storage.Storage;

import task.TaskList;

import ui.Ui;

/**
 * Encapsulates a user instruction to mark a task as done.
 *
 * @author Marcus Low
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a new command to mark a task as done.
     *
     * @param index Index of the task in the task list.
     */
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