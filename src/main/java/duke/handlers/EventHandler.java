package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Event;
import duke.models.Task;
import duke.models.TaskList;

import static duke.services.Ui.dukePrint;

public class EventHandler {
    /**
     * Handles the EVENT Duke command.
     * Adds an Event into the provided list containing description and at date provided in input.
     *
     * @return Response of the executed EVENT Command.
     * @param list: TaskList to add the Event in.
     * @param input: Event description and date.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        String[] eventInputs = input.split(" /at ", 2);
        if (eventInputs.length < 2 || eventInputs[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        assert eventInputs.length == 2;
        Task newTask = new Event(eventInputs[0], eventInputs[1]);
        list.add(newTask);
        return ("Event Added!");
    }
}
