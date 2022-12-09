package duke;

import javafx.application.Platform;

/**
 * Encapsulates a 'bye' user command.
 */
public class ByeCommand extends Command {
    private static final int ARGS_REQUIRED = 1;
    private Ui ui;
    private TaskList tasks;



    /**
     * Constructor for a <code>ByeCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param numberOfArguments The number of arguments in the user input.
     * @throws DukeException If there is an invalid number of arguments provided.
     */
    public ByeCommand(Ui ui, TaskList tasks, int numberOfArguments) throws DukeException {
        if (numberOfArguments > ARGS_REQUIRED) {
            throw new DukeException("Invalid number of arguments, Usage: bye\n");
        }
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Executes the given command.
     *
     * @return A string encapsulating the response from Duke.
     */
    @Override
    public String execute() {
        Platform.exit();
        return null;
    }
}
