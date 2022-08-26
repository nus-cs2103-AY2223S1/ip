package task;

import task.Task;

/**
 * This class encapsulates the idea of a event.
 */
public class Event extends Task {
    private String duration;

    /**
     * Constructor for a event.
     * @param description what the event is
     * @param status whether it has been done
     * @param duration when the event will be held
     */
    public Event(String description, boolean status, String duration) {
        super(description, status);
        this.duration = duration;
    }

    /**
     * Converts the event to string representation to be stored in text file.
     * @return a string
     */
    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "E | " + status + " | " + super.toString() + " | " + duration + "\n";
    }

    /**
     * Converts the event to string representation for user.
     * @return a string
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.toString() + "(at: " + this.duration + ")";
    }
}