package duke.command;


import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * EventCommand creates a new Event task based on the user input.
 */
public class EventCommand extends Command {

    private String input;

    private static final String ddmmyyyyRegex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
    private static final String yyyymmddRegex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
    private static final String timeRegex = "[0-9]{4}";

    /**
     * Creates a new EventCommand.
     *
     * @param input User input from the command line interface.
     */
    public EventCommand (String input) {
        this.input = input;
    }

    /***
     * Creates an event task and stores it.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (!input.contains("/at")) {
            throw new DukeException("duke.Event creation should contain the '/at' tag! Please input again!");
        }
        String[] arr = input.split("/at ", 2);
        Event curr = new Event(arr[0]);

        dateSetter(curr, arr[1]);
        tasks.add(curr);

        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Got it. I've added this task: \n");
        toReturn.append(curr + "\n");
        toReturn.append("Now you have " + tasks.getCount() + " tasks in the list.\n");

        return toReturn.toString();
    }


    /**
     * Sets the date for event task.
     *
     * @param e Event task.
     * @param date Date Event for task.
     * @throws DukeException
     */
    private static void dateSetter(Event e, String date) throws DukeException {
        String[] dateTime = date.split(" ", 2);

        if (dateTime[0].matches(ddmmyyyyRegex)) {
            ddmmyyyyFormatSetter(e, dateTime);
        } else if (dateTime[0].matches(yyyymmddRegex)) {
            yyyymmddFormatSetter(e, dateTime);
        } else {
            throw new DukeException("Please input your deadline properly, I can only accept in dd/mm/yyyy OR yyyy-mm-dd.");
        }

    }

    /**
     * Helper to set date with yyyymmdd format, for deadline task.
     *
     * @param e Event task.
     * @param dateTime User input for date (and time).
     * @throws DukeException
     */
    private static void yyyymmddFormatSetter(Event e, String[] dateTime) throws DukeException{
        String[] info = dateTime[0].split("-");

        String dd = info[2];
        String mm = info[1];
        String yyyy = info[0];

        dd = dd.length() == 1 ? "0" + dd : dd;
        mm = mm.length() == 1 ? "0" + mm : mm;
        LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
        e.setDate(toAdd);

        if (dateTime.length == 2 ) {
            if (dateTime[1].matches(timeRegex)) {
                e.setTime(dateTime[1]);
            }
        }
        if (dateTime.length == 2) {
            if (dateTime[1].matches(timeRegex)) {
                e.setTime(dateTime[1]);
            } else {
                throw new DukeException("Please input your time properly, it should be in 24h time.");
            }
        }
    }


    /**
     * Helper to set date with ddmmyyyy format, for deadline task.
     *
     * @param e Event task.
     * @param dateTime User input for date (and time).
     * @throws DukeException
     */
    private static void ddmmyyyyFormatSetter(Event e, String[] dateTime) throws DukeException{
        String[] info = dateTime[0].split("/");

        String dd = info[0];
        String mm = info[1];
        String yyyy = info[2];

        dd = dd.length() == 1 ? "0" + dd : dd;
        mm = mm.length() == 1 ? "0" + mm : mm;
        LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
        e.setDate(toAdd);

        if (dateTime.length == 2 ) {
            if (dateTime[1].matches(timeRegex)) {
                e.setTime(dateTime[1]);
            }
        }
        if (dateTime.length == 2) {
            if (dateTime[1].matches(timeRegex)) {
                e.setTime(dateTime[1]);
            } else {
                throw new DukeException("Please input your time properly, it should be in 24h time.");
            }
        }

    }



}
