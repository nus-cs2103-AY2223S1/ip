import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * The Deadline class contains information of a Deadline task.
 * @author Sheryl-Lynn Tan (G11)
 */
public class Deadline extends Task {
    protected String by;
    private LocalDate localDate = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (HandleTime.isValidDate(by)) {
            this.localDate = LocalDate.parse(by);
        }
    }
    @Override
    public int[] getDate() {
        return HandleTime.fromStringToDate(by);
    }

    @Override
    public String toString() {
        if (localDate != null) {
            return "[D] " + this.getStatusIcon() + " " + this.description + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy)"));
        } else {
            return "[D] " + this.getStatusIcon() + " " + this.description + " (by: " + by + ")";
        }
    }
}