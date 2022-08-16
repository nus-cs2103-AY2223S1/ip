/**
 * Class to represent "Events" tasks.
 */
public class Event extends Task {
    protected String time;

    /**
     * The constructor for Event task
     * @param description
     * @param isDone
     */
    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * the method to mark as done the Event task
     * @return Event object
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * the method to mark as undone the Event task
     * @return Event object
     */
    @Override
    public Event markUndone() {
        super.markUndone();
        return this;
    }

    /**
     * Overridden toString method for Event task details
     * @return String
     */
    @Override
    public String toString() {
        return "[E]"  + super.toString() + " (at: " + time + ")";
    }

    /**
     * The execute version to process given Event task
     * @param task
     * @param ui
     */
    @Override
    public void execute(TaskList task, UI ui) {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
    }
}
