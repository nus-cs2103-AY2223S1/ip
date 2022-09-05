package commands;

import duke.TaskList;
import duke.Ui;

/**
 * Command for finding related tasks.
 */
public class FindCommand extends Command {

    private TaskList tasks;
    private String toFind;
    private Ui ui;

    /**
     * Returns a new FindCommand.
     * @param tasks TaskList from which matching tasks are found from.
     * @param toFind Type of task to search for.
     * @param ui User Interface class that prints a message to the user.
     */
    public FindCommand(TaskList tasks, String toFind, Ui ui) {
        this.tasks = tasks;
        this.toFind = toFind;
        this.ui = ui;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the Tasks that were found.
     */
    public String execute() {
        return ui.showFound(tasks.find(toFind));
    }

}
