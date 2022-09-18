package kkbot.commands;

import java.util.List;

import kkbot.tasklist.TaskList;
import kkbot.storage.Storage;
import kkbot.tasks.Task;
import kkbot.ui.Ui;

/**
 * Class representing the command when user inputs find command.
 *
 * @author AkkFiros
 */

public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    private String[] phrases;

    /**
     * Constructor for a FindCommand
     * @param phrases the user-input phrases to search for
     */
    public FindCommand(String[] phrases) {
        super();
        this.phrases = phrases;
    }

    /**
     * Returns a command for KKBot when user inputs "find".
     * @param tasks the list of tasks stored by KKBot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the message shown representing all tasks containing the phrases
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.getTasksWithPhrases(phrases);
        return ui.showMatches(matches);
    }
}
