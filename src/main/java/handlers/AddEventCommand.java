package handlers;

import exceptions.DukeException;
import models.Event;
import models.TaskList;

import java.util.regex.Pattern;

public class AddEventCommand implements DukeCommand {
    public Pattern pattern = Pattern.compile("[\\S+]\\s/at\\s[\\S+]");

    public String run (TaskList taskList, String content) throws DukeException {
        if (!pattern.matcher(content).find()) {
            throw new DukeException("Event must be in this format: <Description> /at <DateTime>\n");
        }
        String[] detail = content.split(" /at ", 2);
        Event event = new Event(detail[0], detail[1]);
        taskList.AddTask(event);
        return "Added a event: " + event;
    }
}
