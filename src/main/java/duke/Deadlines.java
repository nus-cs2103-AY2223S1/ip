package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected String description;
    private LocalDateTime dateTime;

    public Deadlines(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    private String dateStr() {
        String formatDateTime = this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return formatDateTime;
    }

    public String savedData() {
        return "D | " + super.savedData() + dateStr() + "\n";
    }

    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + deadline + ")";
        return "[D]" + super.toString() + " (by: " + dateStr() + ")";
    }
}