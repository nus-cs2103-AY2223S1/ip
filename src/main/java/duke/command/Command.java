package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command that can be executed.
 */
abstract public class Command {

    /**
     * Executes this command.
     * @param tasks Task list to be altered during the execution.
     * @param ui UI to display results of the execution.
     * @param storage Storage to store results of the execution to.
     * @throws DukeException If user enters an invalid input.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if Duke application should exit after this command.
     * @return Boolean of whether application should exit.
     */
    public abstract boolean isExit();

}
