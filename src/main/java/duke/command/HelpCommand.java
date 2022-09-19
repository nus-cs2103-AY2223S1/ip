package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * HelpCommand class to represent an instruction to provide a short user guide for the user.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        response = UI.helpResponse();
        UI.help();
    }
}
