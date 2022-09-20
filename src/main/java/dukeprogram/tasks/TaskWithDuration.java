package dukeprogram.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import dukeprogram.parser.DateTimeParser;

/**
 * JobWithDuration describes any form of Task that is associated with a date
 */
public abstract class TaskWithDuration extends Task {
    @JsonProperty("timeString")
    private String timeString;
    @JsonProperty("hasLocalDate")
    private boolean hasValidDateParsed;
    @JsonProperty("prefix")
    private String prefix;

    /**
     * Creates a TaskWithDuration with the given name and dateString.
     * The prefix string describes how to join the name and the dateString
     * @param name name of the DatedJob
     * @param dateString the string that describes when the task is due by
     * @param prefix a label that will be between the name and date to aid interpretation
     */
    public TaskWithDuration(String name, String dateString, String prefix) {
        super(name);
        setDate(dateString);
        this.prefix = prefix;
    }

    protected TaskWithDuration() {
        super();
    }

    /**
     * Sets the date of the DatedJob
     * @param dateString the string that describes the date,
     *                   either in any valid format or none at all
     */
    private void setDate(String dateString) {
        LocalDateTime localDate = DateTimeParser.parse(dateString);
        hasValidDateParsed = localDate != null;
        if (hasValidDateParsed) {
            this.timeString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a"));
        } else {
            this.timeString = dateString;
        }
    }

    /**
     * Retrieves the formatted representation of this task
     */
    @JsonIgnore
    public String getTimeString() {
        if (hasValidDateParsed) {
            return timeString;
        } else {
            return "Invalid " + timeString;
        }
    }

    /**
     * Returns a String that describes the DatedJob
     * If the DatedJob has a date with a parsable time format,
     * then it will print as dd MMM YYYY hh:mm (AM/PM). Otherwise,
     * if the date string could not be interpreted, then the original date string is printed,
     * followed by a tag "[Informal Format]".
     * @return a string in the format "task_name (prefix: dateTimeString)"
     */
    @Override
    public String toString() {
        String formality = hasValidDateParsed ? " [Informal Format]" : "";
        return super.toString()
                + String.format(" (%s: %s%s)", prefix, timeString, formality);
    }
}
