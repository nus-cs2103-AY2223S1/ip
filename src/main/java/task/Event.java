package task;
import java.time.LocalDate;

/**
 * Event class has a date field.
 */
public class Event extends Task {
    protected LocalDate date;

    /**
     * Constructor.
     * @param description Description of the Event.
     * @param date Date associated with the Event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[E][✓] %s (at: %s)", this.description, date);
        } else {
            status = String.format("[E][ ] %s (at: %s)", this.description, date);
        }
        return status;
    }

    /**
     * Check if deadline is today.
     * @return true if deadline is today.
     */
    @Override
    public boolean isToday() {
        return date.isEqual(LocalDate.now());
    }

    /**
     * Returns formatted description of the Deadline.
     * @return string representing formatted description.
     */
    @Override
    public String longDescription() {
        String status;
        String done = this.isDone ? "was at" : "is going to be on";
        status = String.format("Event %s %s %s %d %s %d",
                this.description, done, this.date.getDayOfWeek(),
                this.date.getDayOfMonth() , this.date.getMonth(),
                this.date.getYear());
        return status;
    }
}

