package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;

/**
 * The command to be executed with respect to the user's input.
 */
public abstract class Command {
    private Boolean isExit = false;

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    public abstract String execute(TaskList taskList, Storage storage);

    /**
     * Returns if the Command leads to the exit of the program.
     *
     * @return A boolean representing if the program is to exit.
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
