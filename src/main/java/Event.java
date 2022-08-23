import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    
    Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public boolean isHappeningOnDate(LocalDate localDate) {
        return this.at.equals(localDate);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
