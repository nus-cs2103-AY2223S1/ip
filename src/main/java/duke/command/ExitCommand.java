package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to exit the duke chat bot.
 *
 * @author Tan Jun Wei
 */
public class ExitCommand extends Command {
    /**
     * Constructs an exit command.
     */
    public ExitCommand() {}

    /**
     * Executes the exit command.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showOutput("Bye!");
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return True if this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
