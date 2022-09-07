package duke.command;

import duke.exception.DukeException;
import duke.exception.NoIndexException;
import duke.task.TaskList;

/**
 * A class to represent the mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND = "mark";
    private int targetIndex;

    /**
     * Constructs a new MarkCommand instance.
     *
     * @param description the description of the command.
     * @throws DukeException If no description is given.
     */
    public MarkCommand(String description) throws DukeException {
        if (description.equals("")) {
            throw new NoIndexException();
        }

        int markIndex = Integer.parseInt(description);
        this.targetIndex = markIndex;
    }

    /**
     * Marks the task from the list of tasks
     *     and returns a response message.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     * @throws DukeException If there is a problem executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        return taskList.mark(targetIndex);
    }
}
