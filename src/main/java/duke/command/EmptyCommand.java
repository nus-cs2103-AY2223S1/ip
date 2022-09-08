package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;


/**
 * EmptyCommand is to be returned when the user input Command that
 * cannot be understood by Duke.
 */
public class EmptyCommand extends Command {
    public static final boolean IS_EXIT = false;

    /**
     * Constructs a AddCommand instance without initiating any parameter
     */
    public EmptyCommand() {
    }

    /**
     * Throw InvalidCommandException and to show user the Duke cannot process it.
     *
     * @param taskList unused for EmptyCommand.
     * @param ui unused for EmptyCommand.
     * @param storage unused for EmptyCommand.
     * @throws DukeException if the Command can't match Duke's available Commands.
     */
    @Override
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means.");
    }

    /**
     * Returns false as Empty is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }
}
