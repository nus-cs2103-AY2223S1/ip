import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private String date;
    private String time;
    public Deadline(String name, LocalDate date, LocalTime time) {
        super(name);
        this.date = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.time = DateTimeFormatter.ofPattern("hh:mm a").format(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
