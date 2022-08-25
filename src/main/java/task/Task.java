package task;
/**
 * Class to encapsulate Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * String encapsulates Task description and isDone status.
     * @return String representing Task object
     */
    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[T][✓] %s", this.description);
        } else {
            status = String.format("[T][ ] %s", this.description);
        }
        return status;
    }

    /**
     * Returns long description of the Task.
     * @return Long description of the Task.
     */
    public String longDescription() {
        String status;
        if (this.isDone) {
            status = String.format("Task %s", this.description);
        } else {
            status = String.format("Task %s", this.description);
        }
        return status;
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;

    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isToday() {
        return true;
    }

    public String getDescription() {
        return this.description;
    }
}
