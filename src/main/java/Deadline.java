import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = by;
        String[] split = by.split(" ");
        if (split.length == 1) {
            throw new DateTimeException("Missing time/date");
        }
        String date_s = split[0];
        String time_s = split[1];
        this.date = LocalDate.parse(date_s);
        this.time = LocalTime.parse(time_s);
    }

    @Override
    public String toSaveData() {
        return "D" + " | " + super.toSaveData() + " | " + by;
    }

    @Override
    public String toString() {
        String s_date = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s_time = time.format(DateTimeFormatter.ofPattern("HH:mm"));

        return "[D]" + super.toString() + " (by: " + s_date + " " + s_time + ")";
    }
}
