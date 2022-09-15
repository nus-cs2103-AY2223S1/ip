package Duke.Tasks;

import java.time.LocalDateTime;

/**
 * Abstract class to represent a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Public constructor of Task class.
     * @param description The description of task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Public constructor of Task class.
     * @param description The description of task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the current task status.
     * @param isDone The new task status.
     */
    public void setIsDone(boolean isDone) { this.isDone = isDone; }

    /**
     * Gets the current task status.
     * @return Boolean value of current task status.
     */
    public boolean getIsDone() { return this.isDone; }

    /**
     * Gets the description of the task.
     * @return String of the description.
     */
    public String getDiscription() { return this.description; }

    /**
     * Gets the task status in a formal expression.
     * @return String of formal expression of task status.
     */
    public String outputIsDone() { return String.format("[%s]", isDone ? "X":" "); }

    /**
     * Searches for all tasks containing keyword.
     * @param keyWord Keywords to search all tasks
     * @return Boolean value of existence of keyword in task's description.
     */
    public boolean searchKeyWord(String keyWord) { return this.description.contains(keyWord); }

    @Override
    public String toString() { return this.outputIsDone() + this.getDiscription(); }
    public abstract String save();
    public abstract String getTaskType();
    public abstract LocalDateTime getDateTime();

}
