package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for tasks with a deadline.
 */
public class Deadline extends Task{
    private final String dateTime;
    protected LocalDate byDate;
    protected LocalTime byTime = null;

    public Deadline(String description, String by) {
        super(description);
        this.dateTime = by;
        String[] arr = by.split(" ");
        this.byDate = LocalDate.parse(arr[0], Task.INPUT_DATE_FORMAT);
        if (arr.length != 1) {
            this.byTime = LocalTime.parse(arr[1], Task.INPUT_TIME_FORMAT);
        }
    }

    private String parseDate() {
        String output = this.byDate.format(Task.OUTPUT_DATE_FORMAT);
        if (this.byTime != null) {
            output += " " + this.byTime.format(Task.OUTPUT_TIME_FORMAT);
        }
        return output;
    }

    @Override
    public String format() {
        return "deadline " + this.description + " /by " + this.dateTime + "|" + this.getStatusIcon();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.parseDate() + ")";
    }
}
