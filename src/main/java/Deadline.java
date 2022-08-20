import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    protected String timing;
    protected String date;

    public Deadline(String description, String date, String timing) {
        super(description);
        this.timing = timing;
        this.date = date;
    }

    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(this.date);
        return "[D]" + "[" + super.getStatusIcon() + "]" +  super.toString() + " (by: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing + ")";
    }
}
