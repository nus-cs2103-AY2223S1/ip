package pikachu.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description,isDone);
        this.by = by;
    }

    public String getName() {
        return "D";
    }
    public String timing() {
        return this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timing() + ")";
    }
}
