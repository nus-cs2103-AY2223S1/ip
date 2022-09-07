package duke;

/**
 * Encapsulates a user command to list all current tasks.
 */
public class ListCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for a <code>ListCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     */
    public ListCommand(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Executes the given command.
     *
     * @return A string encapsulating the response from Duke.
     */
    @Override
    public String execute() {
        return this.ui.showTasks(this.tasks);
    }
}
