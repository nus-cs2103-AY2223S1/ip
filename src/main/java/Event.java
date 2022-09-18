import java.time.LocalDateTime;
import java.util.Date;

public class Event extends Task{
    private LocalDateTime time;
    /**
     * Constructor for the Event task.
     *
     * @param name Input name of the task.
     */
    public Event(String name, LocalDateTime dateTime) {
        super(name);
        this.time = dateTime;
    }
    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at: %s)", DateTime.printDate(time));
    }

    @Override
    public String changeFormat() {
        return String.format("E | %s | %s | %s", getStatus(), name, DateTime.changeFormat(this.time));
    }
}
