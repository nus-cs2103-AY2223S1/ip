package tuna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tuna.TunaException;

/**
 * Represents a time based task. A TimeBasedTask object contains a LocalDateTime object indicating the date and time
 * of the task.
 */
public abstract class TimeBasedTask extends Task {
    /** Date and Time of the task */
    private LocalDateTime dateTime;

    /**
     * Creates a time based task.
     *
     * @param taskDescription description of the task.
     * @param taskType the type of the task.
     * @param stringRepresentationOfDateTime LocalDateTime object indicating the date and time of the task.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public TimeBasedTask(String taskDescription, String taskType, String stringRepresentationOfDateTime)
            throws TunaException {
        super(taskDescription, taskType);
        try {
            dateTime = LocalDateTime.parse(stringRepresentationOfDateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (java.time.format.DateTimeParseException e) {
            throw new TunaException("Seems like you formatted the date and time wrongly, remember to format it as "
                    + "such:\nyyyy-mm-dd hh:mm");
        }
    }

    /**
     * Returns the date and time of the task as a LocalDateTime object.
     *
     * @return LocalDateTime of the start of the event.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns formatted representation of LocalDateTime object for display.
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return formatted representation of LocalDateTime.
     */
    public static String parseDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + "hrs";
    }

    /**
     * Returns the start time of the event.
     *
     * @return start time of the event.
     */
    public String getStringRepresentationOfDateTime() {
        return this.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
