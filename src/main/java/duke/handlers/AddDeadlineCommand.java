package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.DeadlineParser;
import duke.models.DukeResponse;
import duke.models.TaskList;

public class AddDeadlineCommand implements DukeCommand {
    private final DeadlineParser deadlineParser = new DeadlineParser();

    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        Deadline deadline = deadlineParser.parseDeadline(content);
        taskList.addTask(deadline);
        return new DukeResponse("Added a deadline: " + deadline);
    }
}
