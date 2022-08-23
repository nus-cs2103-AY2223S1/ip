import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        String[] splitStr = by.trim().split("\\s+");
        LocalDate date = LocalDate.parse(splitStr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime time = LocalTime.parse(splitStr[1], DateTimeFormatter.ofPattern("HH:mm"));
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("HHmm"));
        return "[D]" + super.toString() + " (by: " + formattedDate + " " + formattedTime + ")";
    }
}