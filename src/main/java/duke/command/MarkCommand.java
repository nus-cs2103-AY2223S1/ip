package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a mark task command
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates an mark task command.
     *
     * @param index index of task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task marking message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.markAsDone(index);
    }
}
