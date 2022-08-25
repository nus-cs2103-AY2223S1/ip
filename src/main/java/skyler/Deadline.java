package skyler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }

    @Override
    public String toStringUnformatted() {
        String unformatted = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("[D]%s (by: %s)", super.toString(), unformatted);
    }

    @Override
    public String toString() {
        String formatted = formatDateTime(by);
        return String.format("[D]%s (by: %s)", super.toString(), formatted);
    }
}
