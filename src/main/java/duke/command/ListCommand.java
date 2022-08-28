package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;


public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.list(taskList);
    }
}
