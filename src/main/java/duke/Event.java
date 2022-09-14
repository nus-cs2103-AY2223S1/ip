package duke;

/**
 * Represents a Task that is a Event. A Deadline object is Task with a specific timing.
 */
public class Event extends Task {
    private final String notes;
    private static final String NOTES = "OTOT";
    private final static char STATUS_COMPLETE = 'X';

    public Event(String desc, char taskType) {
        super(desc, taskType);
        assert taskType == 'E';
        notes = getNotes(desc);
    }

    public Event(String desc, char completed, char taskType) {
        super(desc, completed, taskType);
        notes = getNotes(desc);
    }

    /**
     * Creates a Event object based on a given String which describes a Event object.
     * @param desc String description of a Event
     */
    public Event(String desc) {
        super(desc);
        notes = getNotes(desc);
    }

    private String getNotes(String desc) {
        int index = desc.indexOf('/');
        return (index > 0) ? desc.substring(index + 1).trim() : NOTES;
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
