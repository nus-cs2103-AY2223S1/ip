package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String TAG = "E";
    protected LocalDateTime dateAndTime;


    /**
     * Constructor for an event.
     *
     * @param name
     * @param dateAndTime
     */
    public Event(String name, String dateAndTime) {
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
     * Constructor for an event with a known completion status.
     *
     * @param name
     * @param dateAndTime
     * @param isDone
     */
    public Event(String name, String dateAndTime, boolean isDone) {
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
     * Getter for event date and time.
     *
     * @return YYYY-MM-dd HHMM representation of date and time.
     */
    public String getDateString() {
        return this.dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * toString method for an Event.
     *
     * @return To-do list friendly representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TAG, super.toString(), getDateString());
    }

    /**
     * Returns an Event in a save-friendly format.
     *
     * @return save-friendly representation of an Event.
     */
    @Override
    public String savedString() {
        return String.format("%s,%s,%s", TAG, super.savedString(), this.getDateString());
    }
}
