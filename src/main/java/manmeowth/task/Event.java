package manmeowth.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an Event task.
 *
 * @author WR3nd3
 */
public class Event extends Task {

    /** 'at' is the String the Event was constructed with */
    protected String at;
    /** 'formattedTime' is the formatted version of 'by' parsed for date and time */
    protected String formattedTime;

    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * Constructor for the Event task.
     *
     * @param description String representing the description of the event.
     * @param at String representing the time of the event.
     * @param isCompleted boolean representing whether the task is completed.
     */
    public Event(String description, String at, boolean isCompleted) {
        super(TaskId.E, description, isCompleted);
        this.at = at;

        try {
            Pattern date = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
            Matcher dateMatcher = date.matcher(at);
            if (dateMatcher.find()) {
                this.date = LocalDate.parse(dateMatcher.group());
            }
        } catch (DateTimeParseException e) {
            date = null;
        }

        try {
            Pattern time = Pattern.compile("\\d\\d\\d\\d\\z");
            Matcher timeMatcher = time.matcher(at);
            if (timeMatcher.find()) {
                this.time = LocalTime.parse(timeMatcher.group(), DateTimeFormatter.ofPattern("HHmm"));
            }
        } catch (DateTimeParseException e) {
            time = null;
        }
        formattedTime = timeToString();
    }

    /**
     * {@inheritDoc}
     */
    public String summary() {
        String status = isCompleted ? completed : notCompleted;
        String message = id + " | " + status + " | " + description + " | " + at;
        return message;
    }

    /**
     * Returns a string representation of when the event is.
     *
     * @return a string consisting of the event's date or time if available.
     */
    private String timeToString() {
        String when;
        if (date != null && time != null) {
            when = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + time;
        } else if (date != null) {
            when = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (time != null) {
            when = time.toString();
        } else {
            when = at;
        }
        return when;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string consisting of the event completion status and description.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + super.toString() + " (at: " + formattedTime + ")";
    }
}
