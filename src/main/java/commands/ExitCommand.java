package commands;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Exit command for exiting chat bot.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
