package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all unfinished deadline tasks sorted by date
 *
 * @author Pontakorn Prasertsuk
 */
public class ReminderCommand extends Command {

    /**
     * Executes the ReminderCommand
     *
     * @param taskList the task list to be used for the reminder
     * @param ui the user interface to be used
     * @param storage not being used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showOutput("Here are your upcoming deadlines:");
        ui.showOutput(taskList.getDeadlineList().toString());

        return "Here are your upcoming deadlines:\n" + taskList.getDeadlineList().toString();
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
