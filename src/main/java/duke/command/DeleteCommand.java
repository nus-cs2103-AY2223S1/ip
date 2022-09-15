package duke.command;

import duke.exception.DukeException;
import duke.exception.NoIndexException;
import duke.task.TaskList;

/**
 * A class to represent the delete command
 */
public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private int targetIndex;

    /**
     * Constructs a new DeleteCommand instance.
     *
     * @param description the description of the command.
     * @throws DukeException No valid index is given.
     */
    public DeleteCommand(String description) throws DukeException {
        //No Index Given
        if (description.equals("")) {
            throw new NoIndexException();
        }
        int deleteIndex = Integer.parseInt(description);
        targetIndex = deleteIndex;
    }

    /**
     * Deletes the specified task from the list of tasks
     *     and returns a response message.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        return taskList.delete(targetIndex);
    }
}
