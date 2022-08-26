package handlers;

import exceptions.DukeException;
import models.Event;
import models.EventParser;
import models.TaskList;

public class AddEventCommand implements DukeCommand {
   private final EventParser eventParser = new EventParser();

    public String run (TaskList taskList, String content) throws DukeException {
        Event event = eventParser.parseEvnet(content);
        taskList.AddTask(event);
        return "Added a event: " + event.toString();
    }
}
