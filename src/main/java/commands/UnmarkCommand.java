package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Unmark command to unmark a given task in the taskList.
 */
public class UnmarkCommand extends Command {

    protected int index;

    public UnmarkCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        tasks.unmark(index);
        ui.showMessage("OK, I've marked this task as not done yet:\n" + tasks.getTask(index));
    }
}
