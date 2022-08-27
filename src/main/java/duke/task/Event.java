package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class extending Task representing an Event task.
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime time;

    public Event(String content, LocalDate date, LocalTime time) {
        super(content);
        this.date = date;
        this.time = time;
    }

    @Override 
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(), 
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
                        this.time.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    /**
     * Produces a string representation of this Event to be stored in a text file.
     * @return string representation of this Event.
     */
    @Override
    public String toFileData() {
        return String.format("E | %d | %s | %s %s", this.status ? 1 : 0, this.content, 
                this.date.toString(), this.time.toString());
    }
}
