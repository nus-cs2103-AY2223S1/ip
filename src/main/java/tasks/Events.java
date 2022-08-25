package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime timing;

    public Events(String description, String timing) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.timing = LocalDateTime.parse(timing.strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy k:mm"));
    }


    public String toString() {
        String result = "[E]" + "[" + getStatusIcon() + "] " + this.description + "(at: "
                + this.timing.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
        return result;
    }
}
