package duke;

public class Event extends Task {
    private final String timing;

    public Event(String desc, char taskType) {
        super(desc, taskType);
        timing = getTiming(desc);
    }

    public Event(String desc, char completed, char taskType) {
        super(desc, completed, taskType);
        timing = getTiming(desc);
    }

    public Event(String desc) {
        super(desc);
        timing = getTiming(desc);
    }

    private String getTiming(String desc) {
        int index = desc.indexOf('/');
        if (index > 0) {
            return desc.substring(index + 1);
        }
        return "OTOT";
    }

    protected Event performTask() {
        return new Event(this.getDesc(), 'X', this.getTaskType());
    }

    protected Event undoTask() {
        return new Event(this.getDesc(), this.getTaskType());
    }
}
