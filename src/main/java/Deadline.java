import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private String description;
    private LocalDate date;
    private boolean isDone;
    private String type;

    public Deadline(String description, String date) {
        this.description = description;
        this.date = LocalDate.parse(date);
        this.type = "D";
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
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description + "(by: " + getDate() + ")";
    }
     
}
