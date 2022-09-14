package duke;

/**
 * Represents a Task that is a Event. A Deadline object is Task with a specific timing.
 */
public class Event extends Task {
    private final String timing;
    private static final String NO_TIMING = "OTOT";
    private final static char STATUS_COMPLETE = 'X';

    public Event(String desc, char taskType) {
        super(desc, taskType);
        assert taskType == 'E';
        timing = getTiming(desc);
    }

    public Event(String desc, char completed, char taskType) {
        super(desc, completed, taskType);
        timing = getTiming(desc);
    }

    /**
     * Creates a Event object based on a given String which describes a Event object.
     * @param desc String description of a Event
     */
    public Event(String desc) {
        super(desc);
        timing = getTiming(desc);
    }

    private String getTiming(String desc) {
        int index = desc.indexOf('/');
        return (index > 0) ? desc.substring(index + 1) : NO_TIMING;
    }

    /**
     * Creates a Event instance that is identical to a given Deadline object, and then marked as complete.
     * @return An identical Event object that is marked as complete
     */
    protected Event completeTask() {
        return new Event(getDesc(), STATUS_COMPLETE, getTaskType());
    }

    /**
     * Creates a Event instance that is identical to a given Deadline object, and then marked as incomplete.
     * @return An identical Event object that is marked as incomplete
     */
    protected Event resetTask() {
        return new Event(getDesc(), getTaskType());
    }
}
