import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.getMonth()
                + " " + this.date.getDayOfMonth() + " " + this.date.getYear() + ")";
    }
}
