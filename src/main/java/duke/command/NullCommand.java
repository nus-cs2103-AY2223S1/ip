package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;

/**
 * NullCommand class to represent an empty instruction, does nothing.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class NullCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        return;
    }
}
