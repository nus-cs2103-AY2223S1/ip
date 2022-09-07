package duke;

/**
 * Encapsulates a user command to delete a task.
 */
public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    /**
     * Constructor for a <code>DeleteCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public DeleteCommand(Ui ui, TaskList tasks, String userResponse) {
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
            this.tasks.deleteTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showRemoveTaskSuccess(Parser.parseTaskNumber(userResponse), this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
