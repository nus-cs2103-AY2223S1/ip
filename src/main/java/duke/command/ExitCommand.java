package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to end the Duke program.
 *
 * @author tanjiarong
 */
public class ExitCommand extends Command {
    /**
     * Execute the Exit Command.
     *
     * @param taskList Task list that contains all the tasks.
     * @param ui ui prints output to the user.
     * @param storage Storage that stores the data into user's hard drive.
     * @return farewell message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.farewell();
    }

    /**
     * Exits from program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
