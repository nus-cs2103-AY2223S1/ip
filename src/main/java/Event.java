import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime datetime;

    /**
     * Constructor for Event instance
     * @param name Description of event
     * @param datetime Date/time of the event
     */
    public Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    public Event(String[] data) {
        super(data[2], (data[1].equals("1")));
        this.datetime = LocalDateTime.parse(data[3]);
    }

    /**
     * Returns the date/time of the event.
     * @return Date/time of the event
     */
    public String getDatetime() {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Sets the date/time of the event to the input date/time.
     * @param datetime New date/time of the event
     */
    public void setDatetime(String datetime) {
        this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDatetime() + "H)";
    }

    @Override
    public String toStringWritable() {
        return " E |" + super.toStringWritable() + String.format("| %s ", this.datetime);
    }
}
