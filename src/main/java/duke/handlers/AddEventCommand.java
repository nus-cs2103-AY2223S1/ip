package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Event;
import duke.models.EventParser;
import duke.models.TaskList;

public class AddEventCommand implements DukeCommand {
   private final EventParser eventParser = new EventParser();

    public String run (TaskList taskList, String content) throws DukeException {
        Event event = eventParser.parseEvnet(content);
        taskList.addTask(event);
        return "Added a event: " + event.toString();
    }
}
