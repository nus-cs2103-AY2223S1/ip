package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Save command for saving tasks from taskList to date file on hard drive.
 */
public class SaveCommand extends Command {

    public SaveCommand() {
        isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        storage.save(tasks.getTaskList(), ui);
    }
}
