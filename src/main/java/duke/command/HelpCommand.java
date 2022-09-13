package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        response = UI.helpResponse();
        UI.help();
    }
}
