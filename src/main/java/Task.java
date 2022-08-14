/**
 * Class to encapsulate Task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * String encapsulates Task description and isDone status.
     *
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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}