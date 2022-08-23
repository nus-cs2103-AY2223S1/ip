import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(String description, LocalDate time, boolean done) {
        super(description);
        this.time = time;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String save() {
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.time.toString();
    }

    @Override
    public LocalDate getTime() {
        return this.time;
    }
}