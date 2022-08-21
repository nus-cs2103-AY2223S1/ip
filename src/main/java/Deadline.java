import java.time.LocalDateTime;

public class Deadline extends Task {
    /**
     * The due date or time of the Deadline instance.
     */
    protected String by;

    protected LocalDateTime dateTime;

    /**
     * Constructor for a Deadline instance.
     * @param description The description of the Deadline instance.
     * @param by The due date or time of the Deadline instance.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String date = by.split(" ")[0].trim();
        String time = by.split(" ")[1].trim();
        this.dateTime = LocalDateTime.parse(date + "T" + time.substring(0, 2) + ":" + time.substring(2));
    }

    /**
     * Overrides the toString method in the parent class.
     * @return A string representing the current deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
