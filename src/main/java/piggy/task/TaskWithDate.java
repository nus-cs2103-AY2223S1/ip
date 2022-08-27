package piggy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Task that also stores an associated DateTime.
 */
public class TaskWithDate extends Task {

    protected LocalDateTime datetime;
    /** The DateTimeFormatter used for input into this class */
    public static final DateTimeFormatter inDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /** The DateTimeFormatter used for output from this class */
    public static final DateTimeFormatter outDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Creates a new TaskWithDate with the given description and datetime.
     *
     * @param description The description of the task.
     * @param datetime A datetime with format "yyyy-MM-dd HH:mm".
     */
    public TaskWithDate(String description, String datetime) {
        super(description);
        this.datetime = LocalDateTime.from(inDateTimeFormatter.parse(datetime));
    }

    /**
     * Returns the formatted datetime of this task.
     *
     * @return The datetime with format "MMM dd yyyy HH:mm".
     */
    String getDateTime() {
        return datetime.format(outDateTimeFormatter);
    }

    /**
     * Returns a LocalDateTime object from this task's datetime.
     *
     * @return The task's datetime.
     */
    public LocalDateTime getLocalDateTime() {
        return datetime;
    }
}

