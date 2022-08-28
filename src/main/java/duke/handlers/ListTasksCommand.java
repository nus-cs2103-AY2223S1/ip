package duke.handlers;

import duke.models.TaskList;

public class ListTasksCommand implements DukeCommand {

    public String run (TaskList taskList, String content) {
        return taskList.toString();
    }
}
