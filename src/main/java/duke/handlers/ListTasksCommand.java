package duke.handlers;

import duke.models.DukeResponse;
import duke.models.TaskList;

public class ListTasksCommand implements DukeCommand {

    public DukeResponse run (TaskList taskList, String content) {
        return new DukeResponse(taskList.toString());
    }
}
