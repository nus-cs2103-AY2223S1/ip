package duke;

/**
 * Represents a Task that is a Event. A Deadline object is Task with a specific timing.
 */
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

    /**
     * Creates a Event object based on a given String which describes a Event object.
     * @param desc String description of a Event.
     */
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

    /**
     * Creates a Event instance that is identical to a given Deadline object, and then marked as complete.
     * @return An identical Event object that is marked as complete.
     */
    protected Event performTask() {
        return new Event(getDesc(), 'X', getTaskType());
    }

    /**
     * Creates a Event instance that is identical to a given Deadline object, and then marked as incomplete.
     * @return An identical Event object that is marked as incomplete.
     */
    protected Event undoTask() {
        return new Event(getDesc(), getTaskType());
    }
}
