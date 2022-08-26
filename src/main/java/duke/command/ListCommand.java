package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulation of the command of listing out the tasks in the list.
 *
 * @author Sun Ruoxin
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listMessage(tasks);
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
