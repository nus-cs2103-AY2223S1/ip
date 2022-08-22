import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate atDate;
    protected String atTime;

    public Event(String desc, LocalDate atDate, String atTime) {
        super(desc);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(by: " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.atTime + ")";
    }


}

