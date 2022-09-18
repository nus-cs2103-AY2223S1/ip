package kkbot.commands;

import kkbot.storage.Storage;
import kkbot.tasklist.TaskList;
import kkbot.ui.Ui;

/**
 * Class representing the command when user inputs list command.
 *
 * @author AkkFiros
 */

public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Returns a command for kkbot.kkbot when user inputs "list".
     * @param tasks the list of tasks stored by kkbot.kkbot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the message with the list of all tasks in kkbot.kkbot
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAllTasks(tasks);
    }
}
