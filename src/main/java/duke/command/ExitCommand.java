package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to terminate Duke.
 */
public class ExitCommand extends Command {
    public static final String EXIT_RESPONSE = "bye...";

    /**
     * Executes the ExitCommand to terminate Duke.
     *
     * @param tasks TaskList.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return EXIT_RESPONSE;
    }

    /**
     * Returns expected status of Duke after ExitCommand is executed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
