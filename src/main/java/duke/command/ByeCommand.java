package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.Ui;

/**
 * Executes the command to exit the program.
 * @author Jicson Toh
 */
public class ByeCommand extends Command {

    /**
     * Executes the command input.
     *
     * @param tasks    amends task list.
     * @param ui      ui to output feedback.
     * @param storage make changes to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    /**
     * Returns true if exiting program.
     *
     * @return false if still running.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
