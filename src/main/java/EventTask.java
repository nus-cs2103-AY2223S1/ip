/**
 * A DeadlineTask to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventTask extends TaskItem {
    private final String dueDate;

    /**
     * Constructor for EventTask.
     * @param description description of the EventTask.
     * @param dueDate dueDate of the EventTask.
     */
    public EventTask(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate + ")";
    }
}
