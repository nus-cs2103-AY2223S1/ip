package duke.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.models.Event;
import duke.models.FormattedDate;
import duke.models.Task;
import duke.models.TaskList;
import duke.utils.Interval;
import duke.utils.IntervalUtil;

/**
 * A handler class for Event commands.
 */
public class EventHandler {
    private static final Pattern RECURRING_EVENT = Pattern.compile("^(.+?(?=\\/at))\\/at(.+?(?=\\/r))\\/r(.+)");

    /**
     * Handles the EVENT Duke command.
     * Adds an Event into the provided list containing description and at date provided in input.
     *
     * @param list TaskList to add the Event in.
     * @param input Event description and date.
     * @return Response of the executed EVENT Command.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        if (input.contains("/r")) {
            return createRecurringEvent(list, input);
        } else {
            return createNonRecurringEvent(list, input);
        }
    }

    /**
     * Creates a non-recurring event from user input.
     *
     * @param list TaskList to add the Event in.
     * @param input Event description and date.
     * @return Response of non-recurring event.
     **/
    public static String createNonRecurringEvent(TaskList list, String input) throws DukeException {
        String[] eventInputs = input.split(" /at ", 2);
        if (eventInputs.length < 2 || eventInputs[1].trim().equals("")) {
            throw new DukeException("Invalid Event Command format, please try again.");
        }
        assert eventInputs.length == 2;
        FormattedDate atDate = new FormattedDate(eventInputs[1]);
        Task newTask = new Event(eventInputs[0], atDate);
        list.add(newTask);
        return ("Event Added!");
    }

    /**
     * Creates a recurring event from user input.
     *
     * @param list TaskList to add the Event in.
     * @param input Event description and date.
     * @return Response of recurring event.
     **/
    public static String createRecurringEvent(TaskList list, String input) throws DukeException {
        System.out.println(input);
        Matcher m = RECURRING_EVENT.matcher(input);
        if (m.find()) {
            String eventDescription = m.group(1).trim();
            System.out.println(eventDescription);
            FormattedDate atDate = new FormattedDate(m.group(2).trim());
            Interval eventInterval = IntervalUtil.getInterval(m.group(3).trim());
            Task newTask = new Event(eventDescription, false, atDate, eventInterval);
            list.add(newTask);
            return (String.format("Recurring Event every %s added!", eventInterval.toString()));
        } else {
            return ("Invalid Event Command format, please try again.");
        }
    }
}
