import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dateAndTime;

    public Deadline(String deadline, String dateAndTime) {
        super(deadline);
        this.dateAndTime = LocalDate.parse(dateAndTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
