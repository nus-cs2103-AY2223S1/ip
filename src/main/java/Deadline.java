import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;
    protected LocalDate by;

    public Deadline(String name, String by) {
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

<<<<<<< HEAD
    public String getBy() {
        return this.by;
=======
    public void changeDateFormat() {
        this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by:" + by + ")";
                "(by: " + by + ")";
    }
}
