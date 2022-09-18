package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents a find task command.
 */
public class FindTaskCommand implements DukeCommand {

    /**
     * Find a task whose description matches the user input.
     *
     * @param taskList The tasklist to be serached.
     * @param s The user input specifying the keyword to look for.
     * @return The DukeResponse containing the found tasks.
     */
    @Override
    public DukeResponse run(TaskList taskList, String s) {
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
