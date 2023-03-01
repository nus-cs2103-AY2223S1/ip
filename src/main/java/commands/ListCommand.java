package commands;

import duke.TaskList;
import duke.Ui;

/**
 * Command for printing lists.
 */
public class ListCommand extends Command {

    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new ListCommand.
     * @param tasks TaskList to be printed.
     * @param ui User interface that prints a message to the user.
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the Tasks in the TaskList.
     */
    public String execute() {
        return this.ui.showTaskList(this.tasks);
    }
}
