package dukeprogram;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dukeprogram.parser.DateTimeParser;

/**
 * DatedJob describes any form of Task that is associated with a date
 */
public abstract class DatedJob extends Task {
    private String timeString;
    private LocalDateTime localDate;
    private final String prefix;

    /**
     * Creates a DatedJob with the given name and dateString.
     * The prefix string describes how to join the name and the dateString
     * @param name name of the DatedJob
     * @param dateString the string that describes when the task is due by
     * @param prefix a label that will be between the name and date to aid interpretation
     */
    public DatedJob(String name, String dateString, String prefix) {
        super(name);
        setDate(dateString);
        this.prefix = prefix;
    }

    /**
     * Sets the date of the DatedJob
     * @param dateString the string that describes the date,
     *                   either in any valid format or none at all
     */
    private void setDate(String dateString) {
        this.localDate = DateTimeParser.parse(dateString);
        this.timeString = localDate == null
                ? dateString
                : localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
    }

    /**
     * Retrieves the LocalDateTime of this DatedJob
     */
    public LocalDateTime getDate() {
        return localDate;
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
        String formality = localDate == null ? " [Informal Format]" : "";
        return super.toString()
                + String.format(" (%s: %s%s)", prefix, timeString, formality);
    }
}
