package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.Storage;
import duke.models.TaskList;

public class SaveCommand implements DukeCommand {

    @Override
    public DukeResponse run(TaskList taskList, String s) throws DukeException {
        int numOfTasksSaved = Storage.saveTaskToDisk(taskList);
        return new DukeResponse(String.format("Successful saved %d tasks to disk!", numOfTasksSaved));
    }
}
