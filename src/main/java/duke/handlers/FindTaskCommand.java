package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

public class FindTaskCommand implements DukeCommand {

    @Override
    public DukeResponse run(TaskList taskList, String s) throws DukeException {
        TaskList matchedTasks = taskList.findTasks(s);
        if (matchedTasks.size() == 0) {
            return new DukeResponse(matchedTasks.toString());
        }
        return new DukeResponse(
                String.format(
                        "Found %d matching tasks: \n" + matchedTasks,
                        matchedTasks.size())
        );
    }
}
