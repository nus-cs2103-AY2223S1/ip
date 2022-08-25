package task;
import java.time.LocalDate;

/**
 * Deadline class has a by field
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructor.
     * @param description Description of the Deadline.
     * @param by Date associated with the Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[D][✓] %s (by: %s)", this.description, date);
        } else {
            status = String.format("[D][ ] %s (by: %s)", this.description, date);
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
        String done = this.isDone ? "was completed at" : "is to be completed by";
        status = String.format("Deadline %s %s %s %d %s %d",
                this.description, done, this.date.getDayOfWeek(),
                this.date.getDayOfMonth() , this.date.getMonth(),
                this.date.getYear());
        return status;
    }
}

