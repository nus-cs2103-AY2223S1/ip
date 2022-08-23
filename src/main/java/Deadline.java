import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task
 * with a specific deadline.
 */
public class Deadline extends Task {
    private String ddl;
    private LocalDate deadline;

    /**
     * Constructs a Deadline object.
     * @param description description for the deadline.
     * @param deadline string that represents deadline of task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            LocalDateTime localDateTime;
            localDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
            this.deadline = localDateTime.toLocalDate();
            this.ddl = localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                String[] strings = deadline.split(" ");
                this.deadline = LocalDate.parse(strings[0]);
                this.ddl = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                if (strings.length > 1) {
                    this.ddl += " " + deadline.substring(deadline.indexOf(" ") + 1);
                }
            } catch (DateTimeParseException e2) {
                this.ddl = deadline;
            }
        }
    }

    @Override
    public boolean compareDate(LocalDate date) {
        return date.equals(this.deadline);
    }

    /**
     * Overriding method of toString() for Deadline.
     * @return the string representing Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
