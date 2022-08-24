import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String type = "[D]";
    protected LocalDate date;
    protected LocalTime time;

    Deadline(String description, String date, String time) {
        super(description);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + time.format(DateTimeFormatter.ofPattern("hhmma")) + ")";
    }

    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("D|%s|%s|%s|%s\n", isDone, super.description, date, time);
    }
}
