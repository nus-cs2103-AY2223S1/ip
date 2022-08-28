package neo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(by);
        return "[D]" + super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
