import java.time.LocalDate;

/**
 * Deadline class has a by field
 */
public class Deadline extends Task implements DateableTask{
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[D][✓] %s", this.description);
        } else {
            status = String.format("[D][ ] %s (by: %s)", this.description, by);
        }
        return status;
    }

    @Override
    public int numOfDaysLeft() {
        return by.compareTo(LocalDate.now());
    }
}