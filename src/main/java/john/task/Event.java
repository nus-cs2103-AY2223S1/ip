package john.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Event task.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for a Event task.
     *
     * @param description The description of the event.
     * @param timing The date and time of when the event is happening.
     */
    public Event(String description, String timing) {
        super(description);
        String[] timingParams = timing.split(" ");
        if (timingParams.length == 2) {
            this.time = LocalTime.parse(timingParams[1], Task.FORMATTER_INPUT_TIME);
        }
        this.date = LocalDate.parse(timingParams[0], Task.FORMATTER_INPUT_DATE);
    }
    @Override
    protected String getType() {
        return "E";
    }

    /**
     * Returns true if the event date is equal to a specified date.
     *
     * @param date The date to equate to.
     * @return True if the event is equal to the specified date, false otherwise.
     */
    @Override
    public boolean isEqualDate(LocalDate date) {
        return this.date.equals(date);
    }

    /**
     * Returns a string representing the storage format of the event.
     *
     * @return A string representing the storage format of the event.
     */
    @Override
    public String toStorageFormat() {
        String completionStatus = isCompleted ? "1" : "0";
        String date = this.date.format(Task.FORMATTER_INPUT_DATE);
        String time = this.time == null ? "" : " " + this.time.format(Task.FORMATTER_INPUT_TIME);
        return String.format("E | %s | %s | %s%s", completionStatus, description, date, time);
    }

    /**
     * A string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        String completionStatus = isCompleted ? "X" : " ";
        String date = this.date.format(Task.FORMATTER_OUTPUT_DATE);
        String time = this.time == null ? "" : " " + this.time.format(Task.FORMATTER_OUTPUT_TIME);
        return String.format("[%s][%s] %s (at: %s%s)", this.getType(), completionStatus, description, date, time);
    }
}
