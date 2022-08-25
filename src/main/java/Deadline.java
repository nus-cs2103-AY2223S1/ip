import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;
    protected String deadlineString;

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadlineString = deadline;
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getTime() {
        int len = deadlineString.length();
        return deadlineString.substring(len - 4);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDayOfWeek() + " " + deadline.getDayOfMonth() + " " +
                deadline.getMonth() + " " + deadline.getYear() + " " + this.getTime() + ")";
    }

    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "D" + " | " + done + " | " + this.description + " | " + this.deadlineString + "\n";
    }
}
