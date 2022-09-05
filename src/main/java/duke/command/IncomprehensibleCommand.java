package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * A class to handle incomprehensible commands.
 */
public class IncomprehensibleCommand extends Command {

    /**
     * Expresses confusion.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means T.T");
    }

    /**
     * Ensures program will not exit.
     *
     * @return a boolean indicating that it should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
