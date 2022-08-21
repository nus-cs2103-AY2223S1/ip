import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DeadlineTask extends Task {

    protected LocalDate dateline;

    /** Constructor for deadline task.
     *
     * @param description task description
     * @param dateline by when this task has to be completed
     * @return a new DeadlineTask
     */
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
