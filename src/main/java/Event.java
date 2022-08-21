import java.time.LocalDate;

/**
 * Event class has a date field.
 */
public class Event extends Task implements DateableTask{
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
    public int numOfDaysLeft() {
        return date.compareTo(LocalDate.now());
    }
}
