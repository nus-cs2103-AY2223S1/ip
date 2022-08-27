package commands;

import exception.FredException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class SaveCommand extends Command {

    public SaveCommand() {
        isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        storage.save(tasks.getTaskList(), ui);
    }
}
