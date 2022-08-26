package handlers;

import exceptions.DukeException;
import models.Deadline;
import models.DeadlineParser;
import models.TaskList;

public class AddDeadlineCommand implements DukeCommand {
    private final DeadlineParser deadlineParser = new DeadlineParser();

    public String run (TaskList taskList, String content) throws DukeException {
        Deadline deadline = deadlineParser.parseDeadline(content);
        taskList.AddTask(deadline);
        return "Added a deadline: " + deadline;
    }
}
