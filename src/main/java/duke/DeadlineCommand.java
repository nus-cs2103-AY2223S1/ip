package duke;

/**
 * Encapsulates a user command to create a deadline.
 */
public class DeadlineCommand extends Command {
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
    public DeadlineCommand(Ui ui, TaskList tasks, String userResponse) {
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
