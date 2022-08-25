import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDayOfWeek() + " " + deadline.getDayOfMonth() + " " +
                deadline.getMonth() + " " + deadline.getYear() + " " + deadline.getHour() + deadline.getMinute() + ")";
    }
}
