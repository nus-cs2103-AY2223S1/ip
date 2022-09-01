package main.java;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
// got date
public class Deadlines extends main.java.Task {

    protected LocalDate by;

    public Deadlines(String taskName, String by) throws DateTimeParseException {
        super(taskName);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
