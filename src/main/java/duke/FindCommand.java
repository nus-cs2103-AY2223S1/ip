package duke;

/**
 * Encapsulates a user command to find all matching tasks.
 */
public class FindCommand extends Command {
    private static final int ARGS_REQUIRED = 2;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;



    /**
     * Constructor for a <code>FindCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     * @param numberOfArguments The number of arguments in the user input.
     * @throws DukeException If an invalid number of arguments is provided.
     */
    public FindCommand(Ui ui, TaskList tasks, String userResponse, int numberOfArguments) throws DukeException {
        if (numberOfArguments < ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments. Usage: find [query_string]");
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
        return this.ui.showMatchingTasks(Parser.parseSearchInput(userResponse), this.tasks);
    }
}
