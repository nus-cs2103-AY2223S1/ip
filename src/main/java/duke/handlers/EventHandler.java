package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.*;
import duke.utils.Interval;
import duke.utils.IntervalUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventHandler {

    private static final Pattern RECURRING_EVENT = Pattern.compile("^(.+?(?=\\ \\/at))\\ \\/at (.+?(?=\\ \\/r))\\ \\/r (.+)");

    /**
     * Handles the EVENT Duke command.
     * Adds an Event into the provided list containing description and at date provided in input.
     *
     * @return Response of the executed EVENT Command.
     * @param list: TaskList to add the Event in.
     * @param input: Event description and date.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        if (input.contains("/r")) {
            return createRecurringEvent(list, input);
        } else {
            return createNonRecurringEvent(list, input);
        }
    }

    public static String createNonRecurringEvent(TaskList list, String input) throws DukeException {
        String[] eventInputs = input.split(" /at ", 2);
        if (eventInputs.length < 2 || eventInputs[1].trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        assert eventInputs.length == 2;
        Task newTask = new Event(eventInputs[0], eventInputs[1]);
        list.add(newTask);
        return ("Event Added!");
    }

    public static String createRecurringEvent(TaskList list, String input) throws DukeException {
        System.out.println(input);
        Matcher m = RECURRING_EVENT.matcher(input);
        if (m.find()) {
            String eventDescription = m.group(1);
            System.out.println(eventDescription);
            String eventDate = m.group(2);
            Interval eventInterval = IntervalUtil.getInterval(m.group(3));
            Task newTask = new Event(eventDescription, false, eventDate, eventInterval);
            list.add(newTask);
            return (String.format("Recurring Event every %s added!", eventInterval.toString()));
        } else {
            return ("No match with event format.");
        }
    }
}
