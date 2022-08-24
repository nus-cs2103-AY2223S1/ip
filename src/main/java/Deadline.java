import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
