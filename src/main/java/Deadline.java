import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadlineDateTime;

    public Deadline(String description, boolean isDone, LocalDateTime deadlineDateTime) {
        super(description, isDone);
        this.deadlineDateTime = deadlineDateTime;
    }

    private String changeDeadlineFormat() {
       return deadlineDateTime.format(DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a"));
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + changeDeadlineFormat() + ")";
    }
}