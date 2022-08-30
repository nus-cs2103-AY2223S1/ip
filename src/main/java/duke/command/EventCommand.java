package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Event;

/**
 * Represents a command to add an Event to the TaskList.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new EventCommand.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public EventCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    /**
     * Adds the Event to the TaskList.
     *
     * @return String representation of the message in response to the command.
     * @throws DukeException If the description of the Event is invalid.
     */
    @Override
    public String run() throws DukeException {
        String[] splitMessage = this.content.split(" /at ", 2);
        if (splitMessage.length < 2) {
            throw new DukeException("You forgot to add the time!");
        }
        return this.tasks.addTask(new Event(splitMessage[0], splitMessage[1]));
    }
}
