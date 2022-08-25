import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDate deadlineDate;

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
