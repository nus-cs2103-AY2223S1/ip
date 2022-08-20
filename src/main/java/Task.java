import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Creates a task
     * @param description description of the task
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the description of the task
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the task
     * @return status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public static String parseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
