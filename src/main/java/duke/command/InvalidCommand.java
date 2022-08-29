package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import exception
import duke.exception.DukeCommandNotFoundException;

/**
 * Represents InvalidCommand object to be called when user inputs an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws a DukeCommandNotFoundException.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeCommandNotFoundException when the command given is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeCommandNotFoundException {
        throw new DukeCommandNotFoundException();
    }
}
