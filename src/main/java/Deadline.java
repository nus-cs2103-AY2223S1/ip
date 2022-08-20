import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a task with a deadline
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = LocalDate.parse(by);
    }

    public String getBy() {
        return this.by.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + parseDate(by) + ")";
    }
}
