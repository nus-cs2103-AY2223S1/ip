import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;

    public Event(String description, LocalDate at, LocalTime timeStart, LocalTime timeEnd, TaskType type) {
        super(description, type);
        this.at = at;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + at + " " + timeStart + "-" + timeEnd;
    }

    public boolean isDateEqual(LocalDate date) {

        return at.isEqual(date);
    }

    @Override
    public String toString() {
        String str = "";
        str += timeStart.format(DateTimeFormatter.ofPattern("HH:mm ")) + "to ";
        str += timeEnd.format(DateTimeFormatter.ofPattern("HH:mm "));
        str += at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(at: " + str + ")";
    }
}
