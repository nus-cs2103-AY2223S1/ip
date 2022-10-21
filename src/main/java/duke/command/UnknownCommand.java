package duke.command;

import duke.DukeException;
import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents an unknown command. Utilises when user input is not recognised by duke.
 */
public class UnknownCommand extends Command {

    private final boolean isQuestionRaised;

    /**
     * Constructs UnknownCommand object.
     *
     * @param command Command of the user input.
     */
    public UnknownCommand(String command) {
        super(command);
        this.isQuestionRaised = false;
    }

    /**
     * Constructs UnknownCommand object.
     *
     * @param command command of the user input.
     * @param isPreviousAnomalies A boolean value to check whether the unknown command is after anomaly raised.
     */
    public UnknownCommand(String command, boolean isPreviousAnomalies) {
        super(command);
        this.isQuestionRaised = isPreviousAnomalies;
    }

    /**
     * Returns string to inform user unknown input is key-in.
     *
     * @param taskList Stores the list of tasks.
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of suitable response according to the user input through BotUI object.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) throws DukeException {
        if (this.isQuestionRaised) {
            return ui.unknownResponseDetected();
        } else {
            return ui.invalidFormat();
        }
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
     * Provides flexibility to UnknownCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public UnknownCommand resolveAnomaly() {
        return this;
    }
}
