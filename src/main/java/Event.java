import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Event extends Task {
    protected String type = "[E]";
    protected LocalDate date;
    protected LocalTime from;
    protected LocalTime to;

    Event(String description, String date, String from, String to) {
        super(description);
        this.date = LocalDate.parse(date);
        this.from = LocalTime.parse(from);
        this.to = LocalTime.parse(to);
    }

    @Override
    public String toString() {
        return type + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + from.format(DateTimeFormatter.ofPattern("HHmm")) + " to "
                + to.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
    }

    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("E|%s|%s|%s|%s|%s\n", isDone, super.description, date, from, to);
    }
}
