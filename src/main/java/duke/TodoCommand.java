package duke;

/**
 * Encapsulates a user command to create a todo.
 */
public class TodoCommand extends Command {
    private static final int ARGS_REQUIRED = 2;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;
    private Storage storage;


    /**
     * Constructor for a <code>TodoCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public TodoCommand(Ui ui, TaskList tasks, String userResponse,
                       Storage storage, int numberOfArguments) throws DukeException {
        if (numberOfArguments < ARGS_REQUIRED) {
            throw new DukeException("The description of a todo cannot be empty. Usage: todo [task description]\n");
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
            this.tasks.addTasks(Parser.parseTodoTask(userResponse));
            this.storage.saveTasks(this.tasks);
            return this.ui.showAddTaskSuccess(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
