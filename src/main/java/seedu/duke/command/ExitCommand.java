package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

/**
 * Command that handles "bye" user input.
 */
public class ExitCommand extends Command {

    /**
     * Prints the Exit message of Duke
     *
     * @param tasks             TaskList of Duke
     * @param ui                Ui of Duke
     * @param storage           Storage of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * This is the exit command, hence this method is overridden.
     *
     * @return      true as this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
