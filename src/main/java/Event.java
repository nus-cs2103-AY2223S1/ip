import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    private String printDate() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy").format(at);
    }

    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "E", super.stringify(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.printDate());
    }
}
