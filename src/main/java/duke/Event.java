package duke;

/**
 * Represents an Event that can be created by the user
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constructor used to create a new event object
     * @param s Command containing task message and time
     * @param isCompleted whether the task is completed
     */
    public Event(String s, boolean isCompleted) {
        super(s.split("/at")[0].substring(5).strip(), isCompleted);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        date = LocalDateTime.parse(s.split("/at")[1].strip(), formatter);
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[X]" : "[ ]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: "
            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Returns a formatted version of the task to store in memory
     * @return formatted string representation of task
     */
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "E | " + completion + " | " + this.getTaskName() + " | "
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(date);
    }
}
