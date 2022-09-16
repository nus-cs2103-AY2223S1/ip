package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TimeTask which is a task that has an
 * element of time
 */
public class TimeTask extends Task {

    public LocalDateTime date;

    /**
     * Instantiates a new TimeTask
     */
    public TimeTask(String description, String icon, LocalDateTime date) {
        super(description, icon);
        assert date != null : "date for a TimeTask cannot be null";
        this.date = date;
    }

    /**
     * Converts String format of the date from the
     * LocalDateTime class
     *
     * @return String format of date
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Converts String format of the date from the
     * LocalDateTime class for saving
     *
     * @return String format of date for saving
     */
    public String getDateSave() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns LocalTimeDate object of a TimeTask instance
     *
     * @return LocalTimeDate object of a TimeTask instance
     */
    public LocalDateTime getLocalTimeDate() {
        return date;
    }

    /**
     * Returns a boolean whether two task are equal
     *
     * @return Returns true if tasks are equal
     * else returns False
     */
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }
        if (o instanceof TimeTask) {
            TimeTask c = (TimeTask) o;
            return c.getIcon().equals(icon) && c.getDescription().equals(description)
                    && c.getLocalTimeDate().equals(date);
        } else {
            return false;
        }
    }

}
