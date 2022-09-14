package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDate.parse(date);
    }

    public Event(String instructions) {
        super(instructions.split("/at")[0]);
        String date = instructions.split("/at")[1].trim();
        this.date = LocalDate.parse(date);
    }

    public Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.date = eventDate;
    }

    // prints date in MMM d yyyy format
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDate() + ")";
    }

    @Override
    public String save() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }
}
