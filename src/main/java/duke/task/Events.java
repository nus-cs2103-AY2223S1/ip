package duke.task;

/**
 * Events class which inherits from Task class.
 *
 * @author Kavan
 */
public class Events extends Task {
    private String specificTime;

    /**
     * Constructor for Events class.
     *
     * @param description Description of event.
     * @param specificTime Time of event.
     */
    public Events(String description, String specificTime) {
        super(description);
        assert !specificTime.isEmpty() : "Specific Time should not be empty";
        this.specificTime = specificTime;
    }

    @Override
    public String storedTaskString() {
        return "E|" + String.valueOf(this.isDone) + "|" + this.description + "|" + this.specificTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.specificTime + ")";
    }
}
