package kkbot.commands;

import kkbot.storage.Storage;
import kkbot.tasklist.TaskList;
import kkbot.ui.Ui;

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
        System.exit(0);
        return ui.showCloser();
    }
}
