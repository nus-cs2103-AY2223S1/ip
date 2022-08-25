package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that lists out all the tasks.
 *
 * @author Bryan Ng Zi Hao
 */
public class ListCommand extends Command {

    /**
     * List all the tasks out.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (taskList.size() > 0) {
            System.out.println("\t Here are your tasks in your list:");
            taskList.printString();
        } else {
            ui.formatMessage("You do not have any tasks.");
        }
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
