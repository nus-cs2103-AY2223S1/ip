package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String input;

    public DeadlineCommand (String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!input.contains("/by")) {
                throw new DukeException("duke.Deadline creation should contain the '/by' tag! Please input again!");
            }
            String arr[] = input.split("/by ", 2);
            Deadline curr = new Deadline(arr[0]);
            dateSetter(curr, arr[1]);
            tasks.add(curr);
            System.out.println("Got it. I've added this task: ");
            System.out.println(curr);
            System.out.println("Now you have " + tasks.getCount() + " tasks in the list.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private static void dateSetter(Task t, String date) throws DukeException{
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
