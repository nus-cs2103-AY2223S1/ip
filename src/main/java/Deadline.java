import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
