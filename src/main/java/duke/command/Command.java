package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The command to be executed with respect to the user's input.
 */
public abstract class Command {
    private Boolean isExit = false;

    /**
     * Executes a Command.
     *
     * @param taskList a list of tasks
     * @param storage a location to store the task information
     * @param ui a ui to handle user interactions
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    /**
     * Returns if the Command leads to the exit of the program.
     *
     * @return if the program is to exit
     */
    public Boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Toggles if the program should exit.
     */
    public void toggleIsExit() {
        this.isExit = !this.isExit;
    }
}
