import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    protected String timing;
    protected String date;

    public Event(String description, String date, String timing) {
        super(description);
        this.timing = timing;
        this.date  = date;
    }

    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(this.date);
        return "[E]" + "[" + super.getStatusIcon() + "]" +  super.toString() + " (at: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing +  ")";
    }
}
