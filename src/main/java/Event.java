import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    
    Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String toFileDescription() {
        return "E" + " | " + super.toFileDescription() + " | " + this.at;
    }

    public static Event fromFileDescription(String input) {
        String[] strArray = input.split(" \\| ", 4);
        String description = strArray[2];
        String at = strArray[3];
        LocalDate dateAt = LocalDate.parse(at);
        Event event = new Event(description, dateAt);
        if (strArray[1].equals("1")) {
            event.markDone();
        }
        return event;
    }
    public boolean isHappeningOnDate(LocalDate localDate) {
        return this.at.equals(localDate);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
