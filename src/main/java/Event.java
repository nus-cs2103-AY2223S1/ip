import java.time.LocalDate;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    private String printDate() {
        return String.format("%s %d %d",
                this.at.getMonth().toString().substring(0, 3),
                this.at.getDayOfMonth(),
                this.at.getYear());
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
