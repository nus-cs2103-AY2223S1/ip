import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate date;

    public Event(String task, String date) {
        super(task, "event");
        String[] returnedArray = date.split(" ");
        if (returnedArray.length == 1) {
            this.date = LocalDate.parse(date);
        } else {
            this.date = LocalDate.parse(date,
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString()
                + " (at: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
