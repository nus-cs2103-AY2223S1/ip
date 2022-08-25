package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to be executed by program.
 */
public abstract class Command {
    private final boolean isBye;

    /**
     * Constructor for Command class.
     *
     * @param isBye whether command is an exit command.
     */
    public Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }

    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if error occurs during execution of command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
