package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Delete command for deleting item from taskList.
 */
public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        String taskToDelete = tasks.getTask(index).toString();
        tasks.delete(index);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(taskToDelete);
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
