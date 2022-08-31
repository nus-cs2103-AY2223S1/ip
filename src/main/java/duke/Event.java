package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String dateTime;

    public Event(String description, String dt) throws DateTimeParseException {
        super(description);
        try {
            DateTimeFormatter parserFormats = DateTimeFormatter.ofPattern("[dd/MM/yyyy HHmm][dd MMMM yyyy HH:mm]");
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, parserFormats);
            this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        } catch (DateTimeParseException err) {
            System.out.println("I don't recognise this time format.\nTry using this format next time: dd/MM/yyyy HHmm");
            this.dateTime = dt;
        }
    }

    public String getDatetime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
