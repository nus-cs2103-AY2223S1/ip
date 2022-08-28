package duke.command;

import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {

    private String input;

    public EventCommand (String input) {
        this.input = input;
    }

    /***
     * Creates an event task and stores it.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (!input.contains("/at")) {
            throw new DukeException("duke.Event creation should contain the '/at' tag! Please input again!");
        }
        String[] arr = input.split("/at ", 2);
        Event curr = new Event(arr[0]);

        dateSetter(curr, arr[1]);
        tasks.add(curr);

        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
    }


    /**
     * Sets the date for event task.
     *
     * @param t Event task.
     * @param date Date Event for task.
     * @throws DukeException
     */
    private static void dateSetter(Task t, String date) throws DukeException {
        String[] dateTime = date.split(" ", 2);

        String ddmmyyyyRegex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
        String yyyymmddRegex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
        String timeRegex = "[0-9]{4}";

        if (dateTime[0].matches(ddmmyyyyRegex)) {
            String[] info = dateTime[0].split("/");

            String dd = info[0];
            String mm = info[1];
            String yyyy = info[2];

            dd = dd.length() == 1 ? "0" + dd : dd;
            mm = mm.length() == 1 ? "0" + mm : mm;
            LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
            t.setDate(toAdd);

            if (dateTime.length == 2 ) {
                if (dateTime[1].matches(timeRegex)) {
                    t.setTime(dateTime[1]);
                }
            }
        } else if (dateTime[0].matches(yyyymmddRegex)){
            String[] info = dateTime[0].split("-");

            String dd = info[2];
            String mm = info[1];
            String yyyy = info[0];

            dd = dd.length() == 1 ? "0" + dd : dd;
            mm = mm.length() == 1 ? "0" + mm : mm;
            LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
            t.setDate(toAdd);

            if (dateTime.length == 2) {
                if (dateTime[1].matches(timeRegex)) {
                    t.setTime(dateTime[1]);
                } else {
                    throw new DukeException("Please input your time properly, it should be in 24h time.");
                }
            }
        } else {
            throw new DukeException("Please input your deadline properly, I can only accept in dd/mm/yyyy OR yyyy-mm-dd.");
        }

    }
}
