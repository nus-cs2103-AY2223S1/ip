import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    //protected String by;
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
