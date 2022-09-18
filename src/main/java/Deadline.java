import java.time.LocalDateTime;
import java.util.Date;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for the Deadline task.
     * @param name Input name of the task.
     */
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.deadline = dateTime;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", DateTime.printDate(deadline));
    }
}
