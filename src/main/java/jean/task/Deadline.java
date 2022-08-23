package jean.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.by = dateTime;
        super.numberOfTasks += 1;
    }

    public String getSaveData() {
        return "D|" + (super.isDone ? 1 : 0) + "|" + super.description + "|"
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }
}
