package duke;

/**
 * Encapsulates a user command to create an event.
 */
public class EventCommand extends Command {
    private static final int ARGS_REQUIRED = 4;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;
    private Storage storage;


    /**
     * Constructor for a <code>EventCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public EventCommand(Ui ui, TaskList tasks, String userResponse, Storage storage,
                        int numberOfArguments, int atIndex) throws DukeException {
        if (numberOfArguments < ARGS_REQUIRED || atIndex == -1) {
            throw new DukeException("Incorrect arguments. Usage: event [task_description] /at [event_time]");
        }
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
        this.storage = storage;
    }

    /**
     * Executes the given command.
     *
     * @return A string encapsulating the response from Duke.
     */
    @Override
    public String execute() {
        try {
            this.tasks.addTasks(Parser.parseEventTask(userResponse));
            this.storage.saveTasks(this.tasks);
            return this.ui.showAddTaskSuccess(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
