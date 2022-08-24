import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // original access modifier was protected
    private final LocalDateTime dateTime;

    public Deadline(String description, Command taskCommand, LocalDateTime dateTime) {
        super(description, taskCommand);
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommand.getString() + " " + description + " /by " + by + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ")";
    }
}
