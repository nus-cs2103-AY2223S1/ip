package duke.handlers;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.Deadline;
import duke.models.Task;
import duke.models.TaskList;
import duke.utils.Interval;
import duke.utils.IntervalUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static duke.services.Ui.dukePrint;

public class DeadlineHandler {
    private static final Pattern RECURRING_DEADLINE = Pattern.compile("^(.+?(?=\\/by))\\/by(.+?(?=\\/r))\\/r(.+)");

    /**
     * Handles the DEADLINE Duke command.
     * Adds an Deadline into the provided list containing description and at date provided in input.
     *
     * @return Response of the executed Deadline Command.
     * @param list: TaskList to add the Deadline in.
     * @param input: Deadline description and date.
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
     * @return Response of non-recurring Deadline.
     * @param list: TaskList to add the Deadline in.
     * @param input: Deadline description and date.
     **/
    public static String createNonRecurringDeadline(TaskList list, String input) throws DukeException {
        String[] deadlineInputs = input.split(" /by ", 2);
        if (deadlineInputs.length < 2 || deadlineInputs[1].trim().equals("")) {
            throw new DukeException("No match with Deadline format, please try again.");
        }
        assert deadlineInputs.length == 2;
        Task newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
        list.add(newTask);
        return ("Deadline Added!");
    }

    /**
     * Creates a recurring Deadline from user input.
     *
     * @return Response of recurring Deadline.
     * @param list: TaskList to add the Deadline in.
     * @param input: Deadline description and date.
     **/
    public static String createRecurringDeadline(TaskList list, String input) throws DukeException {
        System.out.println(input);
        Matcher m = RECURRING_DEADLINE.matcher(input);
        if (m.find()) {
            String deadlineDescription = m.group(1).trim();
            System.out.println(deadlineDescription);
            String DeadlineDate = m.group(2).trim();
            Interval DeadlineInterval = IntervalUtil.getInterval(m.group(3).trim());
            Task newTask = new Deadline(deadlineDescription, false, DeadlineDate, DeadlineInterval);
            list.add(newTask);
            return (String.format("Recurring Deadline every %s added!", DeadlineInterval.toString()));
        } else {
            return ("No match with Deadline format, please try again.");
        }
    }
}
