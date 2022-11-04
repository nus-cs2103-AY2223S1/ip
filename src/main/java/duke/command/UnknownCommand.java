package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command is to be executed when the user enters
 * an unknown command keyword.
 */
public class UnknownCommand extends Command {

    /**
     * Sole constructor of UnknownCommand.
     */
    public UnknownCommand() {

    }

    /**
     * Throws a DukeException as the user has given an unknown command keyword.
     *
     * @param tasks the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     * @throws DukeException always thrown.
     */
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry, I don't know what that means.\n"
                + "Did you make a mistake? Please note that commands are case-sensitive.");
    }

    /**
     * Returns false as this is not a terminating Command.
     *
     * @return false.
     */
    public boolean isTerminator() {
        return false;
    }
}
