import java.time.LocalDate;

public class Deadline extends Task{
    /**
     * Deadline object by field which indicates the deadline
     */
    protected LocalDate by;

    /**
     * A constructor to intialize the Deadline object with the description and deadline
     *  @param description The task
     * @param by The deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + by.getMonth() + " " +
                by.getDayOfMonth() + " " + by.getYear() + ")";
    }
}

