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

    public Deadline(String description, int status, String date, String time) {
        this(description, date, time);
        isDone = status == 1;
    }

    private String formatAsMmmDdYyyy() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String formatAs12Hour() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String parseToSaveData() {
        return "D" + "|" + super.parseToSaveData() + "|" + date + "|" + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + formatAsMmmDdYyyy() + " " + formatAs12Hour() + ")";
    }
}
