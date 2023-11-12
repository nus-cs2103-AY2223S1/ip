package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Event;
import duke.models.TaskList;

public class EventHandler {
    /**
     * Handles the Event command.
     * Adds Event task to TaskList.
     * @param taskList TaskList for the event task to be added to.
     * @param input Event to be added to TaskList.
     */
    public static String getResponse(TaskList taskList, String input) throws DukeException {
        String[] event = input.split("/", 2);
        assert event.length == 2: "Invalid input";
        if (event.length < 2 || event[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String eventDescription = event[0];
        String eventTime = event[1].split(" ", 2)[1];
        taskList.add(new Event(eventDescription, eventTime));
        return ("Got it. I've added this task:\n"
                + taskList.get(taskList.size()-1).toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
