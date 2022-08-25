import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDayOfWeek() + " " + deadline.getDayOfMonth() + " " +
                deadline.getMonth() + " " + deadline.getYear() + " " + deadline.getHour() + deadline.getMinute() + ")";
    }
}
