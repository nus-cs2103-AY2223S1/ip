package duke;

/**
 * Encapsulates a user command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    private String userResponse;

    /**
     * Constructor for an <code>UnmarkCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public UnmarkCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    @Override
    public String execute() {
        try {
            this.tasks.unmarkTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showUnmarkSuccess(Parser.parseTaskNumber(userResponse));
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
