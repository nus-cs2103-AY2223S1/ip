package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command class represents the command entered by user.
 */
public abstract class Command {
    /** Indicates whether the command terminates Duke. */
    public boolean isTerminator;

    /**
     * Initializes an instance of a Command.
     *
     * @param isTerminator Indicates whether the command is a terminator.
     */
    protected Command(Boolean isTerminator) {
        this.isTerminator =  isTerminator;
    }

    /**
     * Executes the command with the specified taskList, ui, and storage.
     *
     * @param taskList Specified taskList.
     * @param ui Specified ui.
     * @param storage Specified storage.
     * @throws DukeException If there is any exceptions when the command is executed.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}
