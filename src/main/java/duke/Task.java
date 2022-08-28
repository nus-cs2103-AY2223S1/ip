package duke;

import java.time.LocalDate;

public abstract class Task {
    /**
     * Description of the task.
     */
    private String taskName;
    /**
     * Information of task completion.
     */
    private boolean completed;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }


    public void setComplete() {
        this.completed = true;
    }

    public void setIncomplete() {
        this.completed = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.completed;
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
        return (this.completed ? "[X] " : "[ ] ") + this.taskName;
    }
}
