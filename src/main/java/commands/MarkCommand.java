package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Mark command to mark a given task in the taskList.
 */
public class MarkCommand extends Command {

    protected int index;

    public MarkCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        tasks.mark(index);
        ui.showMessage("Nice! I've marked this task as done:\n" + tasks.getTask(index));
    }
}
