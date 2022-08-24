import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate dateAndTime;

    public Event(String deadline, String dateAndTime) {
        super(deadline);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    public Event(String deadline, String dateAndTime, boolean isDone) {
        super(deadline, isDone);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toFileString() {
        return "E , " + super.toFileString()  + " , " + dateAndTime;
    }
}
