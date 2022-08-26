package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;

/**
 * Represents a list command of task. A <code>ListCommand</code> object
 * will show users the detail of tasks recorded in the TaskRecords.
 */

public class ListCommand extends Command {

    /**
     * Constructs ListCommand object
     *
     * @param command command of the user input
     */
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {
        ui.showList(taskList);
    }

    /**
     * Returns the true/false of the command exit status that
     * will cause duke stop running
     *
     * @return the true/false of the command exit status
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
