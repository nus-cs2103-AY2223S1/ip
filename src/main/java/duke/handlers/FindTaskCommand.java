package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.TaskList;

public class FindTaskCommand implements DukeCommand {

    @Override
    public String run(TaskList taskList, String s) throws DukeException {
        TaskList matchedTasks = taskList.findTasks(s);
        if (matchedTasks.size() == 0) {
            return matchedTasks.toString();
        }
        return String.format("Found %d matching tasks: \n" + matchedTasks, matchedTasks.size());
    }
}
