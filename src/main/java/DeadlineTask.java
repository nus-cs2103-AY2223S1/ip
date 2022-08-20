import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DeadlineTask extends Task {

    protected LocalDate dateline;

    public DeadlineTask(String description, String dateline) {
        super(description);
        this.dateline = LocalDate.parse(dateline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + this.dateline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
