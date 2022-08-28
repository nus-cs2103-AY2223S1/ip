package Duke.tasks;

public abstract class Task {

    private final String taskDesc;
    private boolean completed;

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.completed = false;
    }

    public String getDesc() {
        return this.taskDesc;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public abstract char getTaskType();

    // Marks the task as done
    public void mark() {
        this.completed = true;
    }

    // Marks the task as done
    public void unmark() {
        this.completed = false;
    }

    public String toString() {
        if (completed) {
            return "[X] " + taskDesc;
        } else {
            return "[ ] " + taskDesc;
        }
    }
}
