package duke.functions.storage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import duke.support.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * LoadDeadline class to initiate the loading of a Deadline task into the TaskList.
 *
 * @author lauralee
 */
public class LoadDeadline implements Load {

    private String taskDescription;
    private String timeDescription;
    private String time;

    /**
     * Constructor for LoadDeadline class.
     *
     * @param taskDescription The string format of the task description as saved in storage.
     */
    public LoadDeadline(String taskDescription) {
        int index = taskDescription.indexOf("(by: ");
        int endIndex = taskDescription.lastIndexOf(")");
        this.taskDescription = taskDescription.substring(7, index);
        this.timeDescription = taskDescription.substring(index + 5, endIndex);
    }

    @Override
    public Task loadTask() {
        try {
            DateFormat formatter = new SimpleDateFormat("dd MMMM yy", Locale.ENGLISH);
            Date strDate = formatter.parse(timeDescription);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.time = newDateFormat.format(strDate);
        } catch (ParseException e) {
            DukeException exception = new DukeException.DateTimeException();
            exception.getMessage();
        }
        return new Deadline(this.taskDescription, this.time);
    }
}
