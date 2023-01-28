package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * InvalidCommand class to represent an instruction to reflect an invalid command.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.invalid();
        response = UI.invalidResponse();
    }
}
