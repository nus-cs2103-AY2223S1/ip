package duke;

public class EventTask extends TimeTask {
    private final String eventTime;
    EventTask(String taskname, String eventTime) {
        super(taskname, eventTime);
        this.eventTime = super.toDisplayDate();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime);
    }

    @Override
    String saveFileFormat() {
        return "E###" + super.saveFileFormat();
    }
}
