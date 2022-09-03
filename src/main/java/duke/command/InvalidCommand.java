package duke.command;

import duke.exception.DukeCommandNotFoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents InvalidCommand object to be called when user inputs an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws a DukeCommandNotFoundException.
     * @param tasks
     * @param storage
     * @throws DukeCommandNotFoundException when the command given is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeCommandNotFoundException {
        throw new DukeCommandNotFoundException();
    }
}
