import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime datetime;

    /**
     * Constructor for Deadline instance
     * @param name Description of task
     * @param datetime Deadline of task
     */
    public Deadline(String name, String datetime) {
        super(name);
        this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the deadline of the task.
     * @return Task deadline
     */
    public String getDatetime() {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Sets the deadline of the task to the input deadline.
     * @param datetime New deadline
     */
    public void setDatetime(String datetime) {
        this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDatetime() + "H)";
    }
}
