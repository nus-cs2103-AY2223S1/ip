package duke;

/**
 * Encapsulates a user command to mark a task as done.
 */
public class MarkCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    /**
     * Constructor for a <code>MarkCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public MarkCommand(Ui ui, TaskList tasks, String userResponse) {
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
            this.tasks.markTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showMarkSuccess(Parser.parseTaskNumber(userResponse));
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
