package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to end the Duke program.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class ByeCommand extends Command {

    /**
     * Returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Closes the ui input reader.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.close();
    }
}
