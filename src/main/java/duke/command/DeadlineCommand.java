package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDate;

/**
 * DeadlineCommand creates a new deadline task based on the user input.
 * Adds the new task to the existing task list.
 */
public class DeadlineCommand extends Command {

    /**
     * Command line input information for deadline task.
     */
    private String input;

    private static final String ddmmyyyyRegex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
    private static final String yyyymmddRegex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
    private static final String timeRegex = "[0-9]{4}";

    /**
     * Creates a new DeadlineCommand.
     *
     * @param input
     */
    public DeadlineCommand (String input) {
        this.input = input;
    }

    /**
     * Creates a deadline task and stores it.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for task.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!input.contains("/by")) {
            throw new DukeException("duke.Deadline creation should contain the '/by' tag! Please input again!");
        }
        String[] arr = input.split("/by ", 2);
        Deadline curr = new Deadline(arr[0]);

        dateSetter(curr, arr[1]);
        tasks.add(curr);

        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Got it. I've added this task:\n");
        toReturn.append(curr + "\n");
        toReturn.append("Now you have " + tasks.getCount() + " tasks in the list.\n");

         return toReturn.toString();
    }

    /**
     * Sets the date for deadline task.
     *
     * @param d Deadline task.
     * @param date Date deadline for task.
     * @throws DukeException
     */
    private static void dateSetter(Deadline d, String date) throws DukeException{
        String[] dateTime = date.split(" ", 2);

        if (dateTime[0].matches(ddmmyyyyRegex)) {
            ddmmyyyyFormatSetter(d, dateTime);
        } else if (dateTime[0].matches(yyyymmddRegex)){
            yyyymmddFormatSetter(d, dateTime);
        } else {
            throw new DukeException("Please input your deadline properly, I can only accept in dd/mm/yyyy OR yyyy-mm-dd.");
        }
    }

    /**
     * Helper to set date with yyyymmdd format, for deadline task.
     *
     * @param d Deadline task.
     * @param dateTime User input for date (and time).
     * @throws DukeException
     */
    private static void yyyymmddFormatSetter(Deadline d, String[] dateTime) throws DukeException{
        String[] info = dateTime[0].split("-");

        String dd = info[2];
        String mm = info[1];
        String yyyy = info[0];

        dd = dd.length() == 1 ? "0" + dd : dd;
        mm = mm.length() == 1 ? "0" + mm : mm;
        LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
        d.setDate(toAdd);

        if (dateTime.length == 2 ) {
            if (dateTime[1].matches(timeRegex)) {
                d.setTime(dateTime[1]);
            }
        }
        if (dateTime.length == 2) {
            if (dateTime[1].matches(timeRegex)) {
                d.setTime(dateTime[1]);
            } else {
                throw new DukeException("Please input your time properly, it should be in 24h time.");
            }
        }
    }


    /**
     * Helper to set date with ddmmyyyy format, for deadline task.
     *
     * @param d Deadline task.
     * @param dateTime User input for date (and time).
     * @throws DukeException
     */
    private static void ddmmyyyyFormatSetter(Deadline d, String[] dateTime) throws DukeException{
        String[] info = dateTime[0].split("/");

        String dd = info[0];
        String mm = info[1];
        String yyyy = info[2];

        dd = dd.length() == 1 ? "0" + dd : dd;
        mm = mm.length() == 1 ? "0" + mm : mm;
        LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
        d.setDate(toAdd);

        if (dateTime.length == 2 ) {
            if (dateTime[1].matches(timeRegex)) {
                d.setTime(dateTime[1]);
            }
        }
        if (dateTime.length == 2) {
            if (dateTime[1].matches(timeRegex)) {
                d.setTime(dateTime[1]);
            } else {
                throw new DukeException("Please input your time properly, it should be in 24h time.");
            }
        }

    }



}
