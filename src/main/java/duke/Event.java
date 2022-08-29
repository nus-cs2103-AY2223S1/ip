package duke;

/**
 * Represents an Event that can be created by the user
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    /**
     * Constructor used to create a new event object
     * @param s Command containing task message and time
     * @param isCompleted whether the task is completed
     */
    public Event(String s, boolean isCompleted) {
        super(s.split("/at")[0].substring(5).strip(), isCompleted);
        date = LocalDate.parse(s.split("/at")[1].strip());
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[X]" : "[ ]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: "
            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a formatted version of the task to store in memory
     * @return formatted string representation of task
     */
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "E | " + completion + " | " + this.getTaskName() + " | " + date;
    }
}
