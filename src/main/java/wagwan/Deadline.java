package wagwan;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
* Deadline is a Task with a description and a date.
*
* @author Linus Chui
*/
public class Deadline extends Task {

    protected String deadline;
    private LocalDate dateTime;

    /**
     * A constructor for a deadline
     *
     * @param description the description the deadline.
     * @param deadline the date of the deadline.
     * @throws WagwanException if there is a date format error.
     */
    public Deadline(String description, String deadline) throws WagwanException {
        super(description);
        this.deadline = deadline;
        this.dateTime = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        String deadlineToString = this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "[D]" + super.toString() + "(by: " + deadlineToString + ")";
    }
}
