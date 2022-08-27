import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String dateString) {
        super(description);
        by = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public Deadline(String description, String dateString, boolean isDone) {
        super(description, isDone);
        by = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getSaveFormat() {
        return "D" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " +
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
