package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class IdleCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "I'm sorry, but I don't quite understand what that means.";
    }
}
