package task;

/**
 * This class encapsulates the idea of a event.
 */
public class Event extends Task {
    private final String DURATION;

    /**
     * Constructor for a event.
     *
     * @param description what the event is
     * @param status      whether it has been done
     * @param DURATION    when the event will be held
     */
    public Event(String description, boolean status, String DURATION) {
        super(description, status);
        this.DURATION = DURATION;
    }

    /**
     * Converts the event to string representation to be stored in text file.
     *
     * @return a string
     */
    @Override
    public String getDESCRIPTION() {
        String status = super.getStatus() ? "T" : "F";
        return "E | " + status + " | " + super.toString() + " | " + DURATION + "\n";
    }

    /**
     * Converts the event to string representation for user.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + " (at: " + this.DURATION + ")";
    }
}