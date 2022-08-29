import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String date, String time) {
        super(description);
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDeadline() + ")";
    }
}
