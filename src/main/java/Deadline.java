import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String fileFormat() {
        return String.format("deadline | %s | %s | %b", super.description, by, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
