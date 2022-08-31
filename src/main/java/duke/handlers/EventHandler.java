package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Event;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class EventHandler {

    public static void handle(TaskList taskList, String input) throws DukeException {
        String[] event = input.split("/", 2);
        if (event.length < 2 || event[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        String eventDescription = event[0];
        String eventTime = event[1].split(" ", 2)[1];
        taskList.add(new Event(eventDescription, eventTime));
        dukePrint("Got it. I've added this task:\n" + taskList.get(taskList.size()-1).toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
