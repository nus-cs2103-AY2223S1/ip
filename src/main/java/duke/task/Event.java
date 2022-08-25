package duke.task;

/**
 * Encapsulate a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task{

    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String taskInfo() {
        return "[E] [" + getStatusIcon() + "] " + description + " (at:" + duration + ")";
    }

    @Override
    public String taskSaveInfo() {
        return "E," + getSavedStatusIcon() + "," + description + "," + duration;
    }
}
