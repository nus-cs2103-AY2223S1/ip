package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Executes the command to close duke.
 * @author Lim Ai Lin
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command the user inputs.
     * @param tasks The list of tasks.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage containing all tasks the user has previously input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
