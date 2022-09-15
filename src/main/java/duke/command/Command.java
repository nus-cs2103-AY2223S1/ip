package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that can be executed
 */
public abstract class Command {

    /**
     * Determines if the command should end the program for the user
     *
     * @return boolean that determines whether program should end
     */
    public abstract boolean isExit();

    /**
     * Executes the command
     *
     * @param tasks the list of tasks
     * @param ui the ui used to display messages after executing the command
     * @param storage the local storage
     * @throws DukeException if an exception is thrown when the command is executed
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
