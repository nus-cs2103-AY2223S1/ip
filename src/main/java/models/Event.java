package models;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time,
 * e.g., team project meeting on 2/10/2019 2-4pm
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    protected String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String getTaskTypeIcon() {
        return TaskType.EVENT.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.datetime);
    }
}
