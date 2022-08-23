import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate atDate;

    public Event(String desc, LocalDate atDate) {
        super(desc);
        this.atDate = atDate;
    }

    public String getDescription() {
        return super.description;
    }

    public String getEventAt() {
        return this.atDate.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }


}

