package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private final LocalDateTime time;

    /**
     * Initialises an event object.
     *
     * @param task description of the event
     * @param time time of the event
     */
    public Events(String task, LocalDateTime time) {
        super(task);
        this.time = time;

    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (by: %s)",
                this.getDone()
                        ? "X"
                        : " ", this.getTask(), this.time.format(DateTimeFormatter.ofPattern("MMM, d, YYYY")));
    }
}
