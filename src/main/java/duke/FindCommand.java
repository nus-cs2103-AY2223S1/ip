package duke;

/**
 * Encapsulates a user command to find all matching tasks.
 */
public class FindCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    /**
     * Constructor for a <code>FindCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public FindCommand(Ui ui, TaskList tasks, String userResponse) {
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
        return this.ui.showMatchingTasks(Parser.parseSearchInput(userResponse), this.tasks);
    }
}
