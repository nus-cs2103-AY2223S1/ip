import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Deadline task.
 *
 * @author WR3nd3
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * Constructor for the deadline task.
     *
     * @param description String representing the description of the deadline.
     * @param by String representing the time of the deadline.
     * @param isCompleted boolean representing whether the task is completed.
     */
    public Deadline(String description, String by, boolean isCompleted) {
        super(Task_Id.D, description, isCompleted);
        this.by = by;
        try {
            Pattern date = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
            Matcher dateMatcher = date.matcher(by);
            if(dateMatcher.find()) {
                this.date = LocalDate.parse(dateMatcher.group());
            }
        } catch (DateTimeParseException e) {
            date = null;
        }

        try {
            Pattern time = Pattern.compile("\\d\\d\\d\\d\\z");
            Matcher timeMatcher = time.matcher(by);
            if(timeMatcher.find()) {
                this.time = LocalTime.parse(timeMatcher.group(), DateTimeFormatter.ofPattern("HHmm"));
            }
        } catch (DateTimeParseException e) {
            time = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public String summary() {
        String status = isCompleted ? "1" : "0";
        String message = id + " | " + status + " | " + description + " | " + by;
        return message;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string consisting of the deadline completion status and description.
     */
    @Override
    public String toString() {
        String when;
        if (date != null && time != null) {
            when = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + time;
        } else if (date != null) {
            when = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (time != null) {
            when = time.toString();
        } else {
            when = by;
        }
        return "[" + id + "] " + super.toString() + " (by: " + when + ")";
    }
}