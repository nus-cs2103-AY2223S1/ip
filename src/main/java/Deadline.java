import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.by = dateTime;
        super.numberOfTasks += 1;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }
}
