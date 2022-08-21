import java.time.LocalDate;

/**
 * Event class has a date field.
 */
public class Event extends Task{
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[E][✓] %s", this.description);
        } else {
            status = String.format("[E][ ] %s (at: %s)", this.description, date);
        }
        return status;
    }

    @Override
    public boolean isToday() {
        return date.isEqual(LocalDate.now());
    }

    @Override
    public String longDescription() {
        String status;
        String done = this.isDone? "was": "is";
        status = String.format("Event %s %s at %s %d %s %d",
                this.description, done, this.date.getDayOfWeek(),
                this.date.getDayOfMonth() , this.date.getMonth(),
                this.date.getYear());
        return status;
    }
}
