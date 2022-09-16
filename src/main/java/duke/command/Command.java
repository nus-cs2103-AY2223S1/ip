package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Represents a user command that can be executed.
 */
public abstract class Command {

    /**
     * Returns whether the command is a ByeCommand.
     *
     * @return true if the command is a ByeCommand, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the task, displaying the output on the UI.
     *
     * @param taskList The TaskList for this Duke program.
     * @param ui The Ui for this Duke program.
     * @param storage The Storage for this Duke program.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Executes the task, displaying the output on the GUI.
     *
     * @param taskList The TaskList for this Duke program.
     * @param storage The Storage for this Duke program.
     * @return A String to be displayed to the user on the GUI.
     */
    public abstract String execute(TaskList taskList, Storage storage);

}
