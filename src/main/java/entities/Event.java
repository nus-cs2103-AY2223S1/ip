package entities;

public class Event extends Todo {
    private String deadline;

    public Event(String desc, String deadline) {
        super(desc);
        this.deadline = String.format(" (by: %s)", deadline);
    }

    /**
     * Getter for deadline
     * 
     * @return Deadline of Event
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[E]" + marker + getDescription() + getDeadline();
    }
}
