package duke;

/**
 * Encapsulates a 'bye' user command.
 */
public class ByeCommand extends Command {
    private static final int ARGS_REQUIRED = 1;

    private Storage storage;
    private Ui ui;
    private TaskList tasks;



    /**
     * Constructor for a <code>ByeCommand</code>.
     *
     * @param storage Where the backed up tasks are stored.
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     */
    public ByeCommand(Storage storage, Ui ui, TaskList tasks, int numberOfArguments) throws DukeException {
        if (numberOfArguments > ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments, Usage: bye\n");
        }
        this.storage = storage;
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
        try {
            this.storage.saveTasks(this.tasks);
            return this.ui.showGoodbye();
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }
}
