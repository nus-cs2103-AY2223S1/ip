package duke;

/**
 * Encapsulates a user command to list all current tasks.
 */
public class ListCommand extends Command {
    private static final int ARGS_REQUIRED = 1;
    private Ui ui;
    private TaskList tasks;



    /**
     * Constructor for a <code>ListCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param numberOfArguments The number of arguments in the user input.
     * @throws DukeException If an invalid number of arguments is provided.
     */
    public ListCommand(Ui ui, TaskList tasks, int numberOfArguments) throws DukeException {
        if (numberOfArguments > ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments, Usage: list\n");
        }
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
