package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;

/**
 * Represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public DeleteCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    /**
     * Deletes the task from the TaskList.
     *
     * @return String representation of the message in response to the command.
     * @throws DukeException If the content of the user command does not include an integer.
     */
    @Override
    public String run() throws DukeException {
        String reply;
        try {
            reply = this.tasks.deleteTask(Integer.parseInt(this.content) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number need to be an integer!");
        }
        return reply;
    }
}
