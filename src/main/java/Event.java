import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public boolean isOn(LocalDate date) {
        return this.at.equals(date);
    }

    @Override
    public String toFileRepresentation() {
        return String.format("E | %s | %s", super.toFileRepresentation(), this.at);
    }

    public static Event fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Event result = new Event(description, LocalDate.parse(date));
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}
