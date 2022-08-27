package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

/**
 * Represents a command action to be executed when Roofus runs.
 */
public abstract class Command {

    /**
     * Executes the command to complete the necessary actions.
     *
     * @param taskList TaskList associated with current instance of Roofus.
     * @param storage Storage associated with current instance of Roofus.
     * @param ui Ui associated with current instance of Roofus
     */
    public abstract String execute(
            TaskList taskList, Storage storage, Ui ui);

    /**
     * Checks if Roofus should still be running after command is executed
     *
     * @return boolean Boolean representing whether current instance of
     *     Roofus should terminate.
     */
    public abstract boolean isRunning();
}
