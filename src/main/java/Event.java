/**
 * Encapsulates a task that starts at a specific time and ends at a specific time,
 * e.g., team project meeting on 2/10/2019 2-4pm
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    protected String datetime;
    private static final String TASK_TYPE_ICON = "E";

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String getTaskTypeIcon() {
        return Event.TASK_TYPE_ICON;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.datetime);
    }
}
