package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.parser.DeadlineParser;
import duke.models.DukeResponse;
import duke.models.TaskList;

/**
 * Represents an add deadline command.
 */
public class AddDeadlineCommand implements DukeCommand {
    private final DeadlineParser deadlineParser = new DeadlineParser();

    /**
     * Adds a deadline to the tasklist.
     *
     * @param taskList The tasklist to which the deadline is added to.
     * @param content The user input specifying the detail of the deadline to be added.
     * @return The response containing message about the added deadline.
     * @throws DukeException If an error occurs during parsing the user input.
     */
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        Deadline deadline = deadlineParser.parseDeadline(content);
        taskList.addTask(deadline);
        return new DukeResponse("Added a deadline: " + deadline);
    }
}
