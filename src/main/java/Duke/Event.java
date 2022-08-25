package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at;
    protected LocalDate dateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        dateTime = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        // System.out.println(dateTime.toString());

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String toUser() {
        return "[D]" + super.toString() + "(by: " + this.dateTime.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)) + ")";
    }
}
