package seedu.duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;

    /**
     * A constructor that creates an instance of Deadline.
     *
     * It takes in a description of the task and the deadline of the task.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormatter);
        this.by = dateTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toStore() {
        return "D" + super.toStore() + " : " + by;
    }
}
