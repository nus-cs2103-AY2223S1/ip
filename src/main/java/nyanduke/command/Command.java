package nyanduke.command;

import nyanduke.NyanDukeException;
import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.TaskList;

/**
 * The Command class represents a command that can be
 * issued by the user to NyanDuke.
 */
public abstract class Command {
    /**
     * Checks if a command is a command to exit NyanDuke.
     *
     * @return true only if the command is an exit command.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Executes the command.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message describing the execution of the command.
     * @throws NyanDukeException when the command cannot be successfully executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws NyanDukeException;
}
