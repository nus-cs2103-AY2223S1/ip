package duke;

import java.time.LocalDate;

/**
 * Task class models a task. Where each task has a name and indicator if its completed.
 */
public abstract class Task {
    /**
     * Description of the task.
     */
    private String taskName;
    /**
     * Information of task completion.
     */
    private boolean isComplete;


    /**
     * Creates a new Task instance.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.taskName = name;
        this.isComplete = false;
    }


    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.isComplete;
    }

    public abstract String getTaskType();

    public abstract String getTime();

    public abstract void setTime(String time);

    public abstract void setDate(LocalDate date);

    public abstract String getDateFormat();

    /**
     * Returns the string representation of the task
     *
     * @return Task information.
     */
    @Override
    public String toString() {
        return (this.isComplete ? "[X] " : "[ ] ") + this.taskName;
    }
}
