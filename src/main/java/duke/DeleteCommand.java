package duke;

/**
 * Encapsulates a user command to delete a task.
 */
public class DeleteCommand extends Command {
    private static final int ARGS_REQUIRED = 2;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;
    private Storage storage;


    /**
     * Constructor for a <code>DeleteCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     * @param storage The storage for tasks from the previous session.
     * @param numberOfArguments The number of arguments in the user input.
     * @throws DukeException If an invalid number of arguments is provided.
     */
    public DeleteCommand(Ui ui, TaskList tasks, String userResponse,
                         Storage storage, int numberOfArguments) throws DukeException {
        if (numberOfArguments != ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments. Usage: delete [task_number]");
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
            this.tasks.deleteTask(Parser.parseTaskNumber(userResponse));
            this.storage.saveTasks(this.tasks);
            return this.ui.showRemoveTaskSuccess(Parser.parseTaskNumber(userResponse), this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
