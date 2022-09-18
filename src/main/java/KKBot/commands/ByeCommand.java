package KKBot.commands;

import KKBot.storage.Storage;
import KKBot.tasklist.TaskList;
import KKBot.ui.Ui;

/**
 * Class representing the exit command for KKBot when user inputs 'bye'
 *
 * @author AkkFiros
 */

public class ByeCommand extends Command {
    public static final String KEYWORD = "bye";

    /**
     * Returns KKBot's closer message when user inputs "bye"
     * @param tasks the list of tasks stored by KKBot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the closer message when KKBot is terminated
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCloser();
    }
}
