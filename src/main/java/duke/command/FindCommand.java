package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand class is a Command that gives users a way
 * to find a task by searching for a keyword.
 *
 * @author Edric Yeo
 */
public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for a FindCommand, given a keyword.
     *
     * @param keyword The String representing the matching keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Method that finds matching tasks, given a keyword.
     *
     * @param tasks   The TaskList instance of the task manager.
     * @param ui      The Ui instance of the task manager.
     * @param storage The Storage instance of the task manager.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(this.keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Method that returns the String representation of a FindCommand.
     *
     * @return The String representation of the FindCommand.
     */
    @Override
    public String toString() {
        return "Find command of keyword:" + this.keyword;
    }
}
