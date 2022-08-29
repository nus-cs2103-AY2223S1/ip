package duke.task;

public class EventTask extends TimeTask {
    private final String eventTime;
    public EventTask(String taskname, String eventTime) {
        super(taskname, eventTime);
        this.eventTime = super.toDisplayDate();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }

    @Override
    public String saveFileFormat() {
        return "E###" + super.saveFileFormat();
    }
}
