package duke.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.FormattedDate;
import duke.models.Task;
import duke.models.TaskList;
import duke.utils.Interval;
import duke.utils.IntervalUtil;

/**
 * A handler class for Deadline commands.
 */
public class DeadlineHandler {
    private static final Pattern RECURRING_DEADLINE = Pattern.compile("^(.+?(?=\\/by))\\/by(.+?(?=\\/r))\\/r(.+)");

    /**
     * Handles the DEADLINE Duke command.
     * Adds a Deadline into the provided list containing description and at date provided in input.
     *
     * @param list TaskList to add the Deadline in.
     * @param input Deadline description and date.
     * @return Response of the executed Deadline Command.
     **/
    public static String getResponse(TaskList list, String input) throws DukeException {
        if (input.contains("/r")) {
            return createRecurringDeadline(list, input);
        } else {
            return createNonRecurringDeadline(list, input);
        }
    }

    /**
     * Creates a non-recurring Deadline from user input.
     *
     * @param list TaskList to add the Deadline in.
     * @param input Deadline description and date.
     * @return Response of non-recurring Deadline.
     **/
    public static String createNonRecurringDeadline(TaskList list, String input) throws DukeException {
        String[] deadlineInputs = input.split(" /by ", 2);
        if (deadlineInputs.length < 2 || deadlineInputs[1].trim().equals("")) {
            throw new DukeException("Invalid Deadline Command format, please try again.");
        }
        assert deadlineInputs.length == 2;
        FormattedDate byDate = new FormattedDate(deadlineInputs[1]);
        Task newTask = new Deadline(deadlineInputs[0], byDate);
        list.add(newTask);
        return ("Deadline Added!");
    }

    /**
     * Creates a recurring Deadline from user input.
     *
     * @param list TaskList to add the Deadline in.
     * @param input Deadline description and date.
     * @return Response of recurring Deadline.
     **/
    public static String createRecurringDeadline(TaskList list, String input) throws DukeException {
        System.out.println(input);
        Matcher m = RECURRING_DEADLINE.matcher(input);
        if (m.find()) {
            String deadlineDescription = m.group(1).trim();
            System.out.println(deadlineDescription);
            FormattedDate byDate = new FormattedDate(m.group(2).trim());
            Interval deadlineInterval = IntervalUtil.getInterval(m.group(3).trim());
            Task newTask = new Deadline(deadlineDescription, false, byDate, deadlineInterval);
            list.add(newTask);
            return (String.format("Recurring Deadline every %s added!", deadlineInterval.toString()));
        } else {
            return ("Invalid Deadline Command format, please try again.");
        }
    }
}
