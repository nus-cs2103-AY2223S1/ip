package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The Command class represents a command that can be
 * issued by the user to Duke.
 */
public abstract class Command {
    /**
     * Checks if a command is a command to exit Duke.
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
     * @throws DukeException when the command cannot be successfully executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
