package duke.command;

import duke.DukeException;
import duke.common.AnomaliesManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents a delete command of task. A <code>DeleteCommand</code> object stores
 * the details of the task as a String type Integer. eg. "1"
 *
 */
public class DeleteCommand extends Command {
    private final String detail;

    /**
     * Constructs DeleteCommand object.
     *
     * @param command Command of the user input.
     * @param detail Detail of the user input as String type Integer.
     */
    public DeleteCommand(String command, String detail) {
        super(command);
        this.detail = detail;
    }

    /**
     * Deletes Task from the TaskList.
     *
     * @param taskList Stores the list of tasks.
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface.
     * @param anomaliesManager Responsible to handle anomaly and store command with anomalies.
     * @return String of suitable response according to the user input through BotUI object.
     * @throws DukeException - Thrown when NumberFormatException or IndexOutOfBoundsException is
     *     catch cause by invalid user input. e.g. delete1 or delete someNonIntegerText.
     */
    @Override
    public String execute(TaskList taskList, BotUI ui, AnomaliesManager anomaliesManager) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(detail) - 1;
            return ui.successRemoveTask(taskList, taskList.delete(taskIdx));
        } catch (NumberFormatException ex) {
            throw new DukeException(ui.invalidCheckFormat());
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.taskNotExist(taskList));
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
     * Provides flexibility to DeleteCommand class for future anomaly checking.
     *
     * @return The same object.
     * @see AddCommand class.
     */
    @Override
    public DeleteCommand resolveAnomaly() {
        return this;
    }
}
