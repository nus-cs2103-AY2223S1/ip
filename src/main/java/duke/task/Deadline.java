package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    public static final String TAG = "D";
    protected LocalDateTime dateAndTime;

    /**
     * Constructor for a Deadline.
     *
     * @param name Name of the task
     * @param dateAndTime Deadline of the task
     */
    public Deadline(String name, String dateAndTime) {
        super(name);

        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("[,./-]");

        this.dateAndTime = LocalDateTime.of(
                Integer.parseInt(dateSplit[0]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateAndTimeSplit[1]) / 100,
                Integer.parseInt(dateAndTimeSplit[1]) % 100
        );
    }

    /**
     * Creates a Deadline with a known completion status.
     *
     * @param name Name of the task
     * @param dateAndTime Deadline of the task
     * @param isDone Completion status
     */
    public Deadline(String name, String dateAndTime, boolean isDone) {
        super(name, isDone);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("[,./-]");

        this.dateAndTime = LocalDateTime.of(
                Integer.parseInt(dateSplit[0]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateAndTimeSplit[1]) / 100,
                Integer.parseInt(dateAndTimeSplit[1]) % 100
        );
    }

    /**
     * Getter for deadline date and time.
     *
     * @return YYYY-MM-dd HHMM representation of date and time.
     */
    public String getDateString() {
        return this.dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * toString method for a Deadline.
     *
     * @return To-do list friendly representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TAG, super.toString(), getDateString());
    }

    /**
     * Returns a Deadline in a save-friendly format.
     *
     * @return save-friendly representation of a Deadline.
     */
    @Override
    public String savedString() {
        return String.format("%s,%s,%s", TAG, super.savedString(), getDateString());
    }
}
