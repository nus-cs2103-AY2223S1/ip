import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime datetime;

    /**
     * Constructor for Deadline instance
     * @param data String passed in from Storage
     */
    public Deadline(String[] data) {
        super(data[2], (data[1].equals("1")));
        this.datetime = LocalDateTime.parse(data[3]);
    }

    /**
     * Constructor for Deadline instance
     * @param name Description of task
     * @param datetime Datetime of task
     */
    public Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
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

    @Override
    public String toStringWritable() {
        return " D |" + super.toStringWritable() + String.format("| %s ", this.datetime);
    }
}
