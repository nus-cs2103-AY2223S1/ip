package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a TimeTask
 */
public class TimeTask extends Task {

    public LocalDateTime date;

    /**
     * Instantiates a new TimeTask
     */
    public TimeTask(String description, String icon, LocalDateTime date) {
        super(description, icon);
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

}
