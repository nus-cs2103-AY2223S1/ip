package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that exits the bot.
 *
 * @author Bryan Ng Zi Hao
 */
public class ExitCommand extends Command {

    /**
     * Displays the exit message.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ui.formatMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
