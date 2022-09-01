import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    private String description;
    private LocalDate range;
    private boolean isDone;
    private String type;

    public Event(String description, String range) {
        this.description = description;
        this.range = LocalDate.parse(range);
        this.type = "E";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        String formattedDate = range.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + "(at: " + getDate() + ")";
    }
     
}
