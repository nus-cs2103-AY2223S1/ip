package duke.command;

import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents a CancelCommand.
 * Utilizes when user reject to proceed with the anomaly detected.
 */
public class CancelCommand extends Command {

    /**
     * Constructs AddCommand object.
     *
     * @param command command of the user input.
     */
    public CancelCommand(String command) {
        super(command);
    }

    /**
     * Returns a String to inform user about the cancellation of the previous command.
     *
     * @param taskList Stores the list of tasks.
     * @param ui       Object that responsible in returning necessary formatted String
     *                 to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of suitable response according to the user input through BotUI object.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) {
        return ui.cancelCommand();
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
     * Provides flexibility to CancelCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public CancelCommand resolveAnomaly() {
        return this;
    }
}
