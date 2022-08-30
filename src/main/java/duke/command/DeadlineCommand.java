package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;

/**
 * Represents a command to add a Deadline to the TaskList.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new DeadlineCommand.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public DeadlineCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    /**
     * Adds the Deadline to the TaskList.
     *
     * @return String representation of the message in response to the command.
     * @throws DukeException If the description of the Deadline is invalid.
     */
    @Override
    public String run() throws DukeException {
        String[] splitMessage = this.content.split(" /by ", 2);
        if (splitMessage.length < 2) {
            throw new DukeException("You forgot to add the deadline!");
        }
        return this.tasks.addTask(new Deadline(splitMessage[0], splitMessage[1]));
    }
}
