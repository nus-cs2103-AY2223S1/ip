import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate byDate;
    protected String byTime;

    public Deadline(String desc, LocalDate byDate, String byTime) {
        super(desc);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.byTime + ")";
    }
}
