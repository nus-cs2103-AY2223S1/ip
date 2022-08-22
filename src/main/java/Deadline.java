import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends DatedTask {

    protected LocalDate date;
    protected String by;

    public Deadline(String description, String by) throws DateTimeException {
        super(description, LocalDate.parse(by));
        this.date = LocalDate.parse(by);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy") )+ ")";
    }
}