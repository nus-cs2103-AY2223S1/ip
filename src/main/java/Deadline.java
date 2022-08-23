import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime completeBy;

    public Deadline(String name, LocalDateTime completeBy) {
        super(name);
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        String dateTime = completeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma"));
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
