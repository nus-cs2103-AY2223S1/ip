import java.time.LocalDate;

/**
 * Deadline class has a by field
 */
public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[D][✓] %s", this.description);
        } else {
            status = String.format("[D][ ] %s (by: %s)", this.description, date);
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
        status = String.format("Deadline %s %s at %s %d %s %d",
                this.description, done, this.date.getDayOfWeek(),
                this.date.getDayOfMonth() , this.date.getMonth(),
                this.date.getYear());
        return status;
    }
}