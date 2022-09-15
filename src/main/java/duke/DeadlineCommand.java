package duke;

/**
 * Encapsulates a user command to create a deadline.
 */
public class DeadlineCommand extends Command {
    private static final int ARGS_REQUIRED = 4;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;



    /**
     * Constructor for a <code>DeadlineCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public DeadlineCommand(Ui ui, TaskList tasks, String userResponse,
                           int numberOfArguments, int byIndex) throws DukeException {
        if (byIndex == -1 || numberOfArguments < ARGS_REQUIRED) {
            throw new DukeException("Incorrect arguments. Usage: deadline [task_description] /by [yyyy-mm-dd]");
        }
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    /**
     * Executes the given command.
     *
     * @return A string encapsulating the response from Duke.
     */
    @Override
    public String execute() {
        try {
            this.tasks.addTasks(Parser.parseDeadlineTask(userResponse));
            return this.ui.showAddTaskSuccess(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
