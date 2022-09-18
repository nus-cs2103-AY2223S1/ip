package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.DukeResponse;
import duke.models.Event;
import duke.models.EventParser;
import duke.models.TaskList;

/**
 * Represents a command to addx event.
 */
public class AddEventCommand implements DukeCommand {
   private final EventParser eventParser = new EventParser();

    /**
     * Adds an event to the tasklist.
     *
     * @param taskList The tasklist to which the event is added to.
     * @param content The user input specifying the detail of the event to be added.
     * @return The response containing message about the added event.
     * @throws DukeException If an error occurs during parsing the user input.
     */
    public DukeResponse run (TaskList taskList, String content) throws DukeException {
        Event event = eventParser.parseEvent(content);
        taskList.addTask(event);
        return new DukeResponse("Added a event: " + event.toString());
    }
}
