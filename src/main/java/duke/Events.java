package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String eventTime;
    private LocalDateTime dateTime;

    public Events(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    private String dateStr() {
        String formatDateTime = this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return formatDateTime;
    }

    public String savedData() {
        return "E | " + super.savedData() + dateStr() + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateStr() + ")";
    }
}
