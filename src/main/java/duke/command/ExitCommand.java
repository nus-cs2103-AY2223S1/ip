package duke.command;

import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents an exit command of task. A <code>ExitCommand</code> object
 * will allow user to exit from duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructs ExitCommand object.
     *
     * @param command Command of the user input.
     */
    public ExitCommand(String command) {
        super(command);
    }
    /**
     * Returns the "bye" message through BotUI object.
     *
     * @param taskList Stores the list of tasks.
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of "bye" response through BotUI object.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) {
        return ui.sayBye();
    }

    /**
     * Returns the true/false of the command exit status that
     * will allow user to stop using Duke.
     *
     * @return The true/false of the command exit status.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the same object.
     * This is because this method is temporary used for AddCommand only.
     * Provides flexibility to ExitCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public ExitCommand resolveAnomaly() {
        return this;
    }
}
