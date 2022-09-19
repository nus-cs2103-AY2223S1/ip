package command;

import storage.Storage;

import task.TaskList;

import ui.Ui;

/**
 * Encapsulates a user instruction to exit the bot.
 *
 * @author Marcus Low
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
