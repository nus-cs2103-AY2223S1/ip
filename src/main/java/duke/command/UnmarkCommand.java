package duke.command;

import duke.exception.DukeException;
import duke.exception.NoIndexException;
import duke.task.TaskList;

/**
 * A class representing the unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";
    private int targetIndex;

    /**
     * Constructs a new UnmarkCommand instance.
     *
     * @param description the description of the command.
     * @throws DukeException If description is empty.
     */
    public UnmarkCommand(String description) throws DukeException {
        //No Index Given
        if (description.equals("")) {
            throw new NoIndexException();
        }
        int unmarkIndex = Integer.parseInt(description);
        targetIndex = unmarkIndex;
    }

    /**
     * Unmarks the task from the list of tasks
     *     and returns the response message.
     *
     * @param taskList the list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        return taskList.unmark(targetIndex);
    }
}
