import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDate date;
    LocalTime time;

    public Deadline(String description, String date, String time) throws DateTimeParseException {
        super(description);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    private String formatAsMmmDdYyyy() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String formatAs12Hour() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + formatAsMmmDdYyyy() + " " + formatAs12Hour() + ")";
    }
}
