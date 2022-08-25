package duke.chatbot.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a date and time associated to it.
 */
public abstract class TimedTask extends Task {
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    /** The date and time associated to the task */
    private LocalDateTime dateTime;

    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns true if the argument date string and the date string associated with
     * the task are the same and false otherwise.
     * @param date The date to compared dateTime with.
     * @return A boolean that describes whether the argument date and task date are
     * matching.
     */
    public boolean hasMatchingDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTime.toLocalDate().format(formatter).equals(date);
    }

    /**
     * Returns a string that corresponds to the task date.
     * @return A string that corresponds to the task date.
     */
    protected String getDateString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a string that corresponds to the task time.
     * @return A string that corresponds to the task time.
     */
    protected String getTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm")) + "hrs";
    }

    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return super.encode() + ",,," + dateTime.format(formatter);
    }
}
