import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Deadline extends Task {
    /**
     * A string the describes the due date/time of the deadline.
     */
    private String dueByStr;
    private LocalDate dueByDate = null;
    private LocalTime dueByTime = null;

    /**
     * Constructor for a deadline.
     * @param description the description of the deadline
     * @param dueBy the due date/time of the deadline
     */
    public Deadline(String description, String dueBy) {
        super(description);
        try {
            String[] dateTime = dueBy.split(" ");
            this.dueByDate = LocalDate.parse(dateTime[0],
                    DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            if (dateTime.length == 2) {
                this.dueByTime = LocalTime.parse(dateTime[1],
                        DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
            }
        } catch (DateTimeParseException e) {
            this.dueByStr = dueBy;
        }
    }

    /**
     * Returns a string used to save the task.
     * @return a string used to save the task
     */
    @Override
    public String saveString() {
        String date = this.dueByDate != null
                ? this.dueByDate.toString()
                : this.dueByStr;
        String time = this.dueByTime != null
                ? String.format(" %s", this.dueByTime.format(DateTimeFormatter.ofPattern(("HHmm"))))
                : "";
        return String.format("D | %s | %s%s", super.saveString(), date, time);
    }

    /**
     * Returns a string representation of the deadline.
     * @return a string representing the deadline
     */
    @Override
    public String toString() {
        String date = this.dueByDate != null
                ? this.dueByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : this.dueByStr;
        String time = this.dueByTime != null
                ? String.format(" %s", this.dueByTime.format(DateTimeFormatter.ofPattern("K:mma")))
                : "";
        return String.format("[D]%s (by: %s%s)", super.toString(), date, time);
    }
}
