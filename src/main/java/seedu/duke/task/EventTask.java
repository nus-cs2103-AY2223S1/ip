package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a time. Time is stored as LocalDate if given in YYYY-MM-DD format.
 * Otherwise, time stored as string.
 */
public class EventTask extends Task {
    private LocalDate date = null;
    private String time = null;
    private static final String TYPE = "[E]";

    public EventTask(String name, String date) {
        super(name);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.time = date;
        }
    }

    /**
     * Constructor only for loading task list.
     * @param name
     * @param date
     * @param isDone
     */
    public EventTask(String name, String date, boolean isDone) {
        super(name, isDone);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.time = date;
        }
    }

    public void editTime(String date) {
        try {
            this.date = LocalDate.parse(date);
            time = null;
        } catch (DateTimeParseException e) {
            this.time = date;
            this.date = null;
        }
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns string in the form: [E] task name (on: time)
     * @return
     */
    @Override
    public String toString() {
        if (time == null) {
            return TYPE + super.toString() + " (on: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return TYPE + super.toString() + " (on: " + time + ")";
        }
    }
}
