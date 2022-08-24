package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDateTime;

/**
 * This calss encapsulates an event from the user.
 */
public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", super.getTaskIcon(), super.toString(),
                Parser.formatDateTimeToPrint(this.dateTime));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event that = (Event) o;
            return this.getDescription().equals(that.getDescription())
                    && this.getDateTime().isEqual(that.getDateTime());
        }
        return false;
    }
}
