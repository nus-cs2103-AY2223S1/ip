package duke.command;

import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents a list command of task. A <code>ListCommand</code> object
 * will show users the detail of tasks recorded in the TaskList.
 */

public class ListCommand extends Command {

    /**
     * Constructs ListCommand object.
     *
     * @param command Command of the user input.
     */
    public ListCommand(String command) {
        super(command);
    }
    /**
     * Returns list of Tasks in the TaskList.
     *
     * @param taskList Stores the list of tasks.
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of number-formatted tasks through BotUI object.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) {
        return ui.showList(taskList);
    }

    /**
     * Returns the true/false of the command exit status that
     * will allow user to stop using Duke.
     *
     * @return The true/false of the command exit status.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the same object.
     * This is because this method is temporary used for AddCommand only.
     * Provides flexibility to ListCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public ListCommand resolveAnomaly() {
        return this;
    }
}
