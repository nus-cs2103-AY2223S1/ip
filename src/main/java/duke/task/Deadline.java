package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Encapsulate a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task{

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String taskInfo() {
        if (deadlineTime == null) {
            return "[D] [" + getStatusIcon() + "] " + description + " (by:" +
                    deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
        } else {
            return "[D] [" + getStatusIcon() + "] " + description + " (by:" +
                    deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + deadlineTime +")";
        }

    }

    @Override
    public String taskSaveInfo() {
        if (deadlineTime == null) {
            return "D," + getSavedStatusIcon() + "," + description
                    + "," + deadlineDate;
        } else {
            return "D," + getSavedStatusIcon() + "," + description
                    + "," + deadlineDate + "," + deadlineTime;
        }

    }
}
