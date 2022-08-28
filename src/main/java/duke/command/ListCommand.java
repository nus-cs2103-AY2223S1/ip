package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;

/**
 * ListCommand class to represent an instruction to display the list of tasks stored in TaskList.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.list(taskList);
    }
}
