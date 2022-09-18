package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.Storage;
import duke.models.TaskList;

public class ExitCommand implements DukeCommand {
    @Override
    public DukeResponse run(TaskList taskList, String s) throws DukeException {
        Storage.saveTaskToDisk(taskList);
        return new DukeResponse(null, true);
    }
}
