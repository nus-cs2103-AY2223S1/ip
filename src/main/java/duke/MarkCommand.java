package duke;

/**
 * Encapsulates a user command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final int ARGS_REQUIRED = 2;
    private Ui ui;
    private TaskList tasks;
    private String userResponse;


    /**
     * Constructor for a <code>MarkCommand</code>.
     *
     * @param ui                The user interface for Duke.
     * @param tasks             The list of current tasks.
     * @param userResponse      The input string from the user.
     * @param numberOfArguments The number of arguments in the user input.
     * @throws DukeException If an invalid number of arguments is provided.
     */
    public MarkCommand(Ui ui, TaskList tasks, String userResponse, int numberOfArguments) throws DukeException {
        if (numberOfArguments != ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments, Usage: mark [task number]\n");
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
            this.tasks.markTask(Parser.parseTaskNumber(userResponse));
            return this.ui.showMarkSuccess(Parser.parseTaskNumber(userResponse));
        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
