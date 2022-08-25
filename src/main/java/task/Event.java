package task;

/**
 * Class for an Event task
 */
public class Event extends Task {
    private String at;

    /**
     * @param description - Description of the task
     * @param at - When the event is at
     */
    public Event(String description, String at) {
        super(description, TaskType.EVENT);
        this.at = at;
    }

    @Override
    protected String getTypeIndicator() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }
}
