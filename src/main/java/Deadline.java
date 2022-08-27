import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String description, String dt) {
        super(description);
        try {
            LocalDateTime dtFormatted = LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.dateTime = dtFormatted.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"));
        } catch (DateTimeParseException err) {
            System.out.println("I don't recognise this time format.\nTry using this format next time: dd/MM/yyyy HHmm");
            this.dateTime = dt;
        }
    }

    public String getDatetime() { return this.dateTime; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
