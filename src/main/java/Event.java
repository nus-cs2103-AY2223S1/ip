import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timing;

    public static final String ENCODED_TASK_TYPE = "E";

    public Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    public String getTiming() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
        return dTF.format(timing).toString();
    }

    /**
     * Encodes the timing as the same string representation that the user entered.
     * Removes the T to match that representation.
     *
     * @return The encoded string representation of the event timing
     */
    public String encodeTiming() {
        return timing.toString().replaceAll("T", " ");
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (at: %s)", getStatusIcon(), getDescription(),getTiming());
    }

    /**
     * @return The task-representation of an event written to the file
     */
    @Override
    public String encode() {
        return String.format("%s|%d|%s|%s\n", ENCODED_TASK_TYPE, getIsDone() ? 1 : 0, getDescription(), encodeTiming());
    }
}
