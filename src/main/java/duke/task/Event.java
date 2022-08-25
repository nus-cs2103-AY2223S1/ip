package duke.task;

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
