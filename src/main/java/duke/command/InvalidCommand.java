package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        UI.invalid();
        response = UI.invalidResponse();
    }
}
