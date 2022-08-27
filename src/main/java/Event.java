import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String datetime;

    public Event(String description, String dt) throws DateTimeParseException {
        super(description);
        try {
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.datetime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        } catch (DateTimeParseException err) {
            System.out.println("I don't recognise this time format.\nTry using this format next time: dd/MM/yyyy HHmm");
            this.datetime = dt;
        }
    }

    public String getDatetime() {
        return this.datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime + ")";

    }
}
