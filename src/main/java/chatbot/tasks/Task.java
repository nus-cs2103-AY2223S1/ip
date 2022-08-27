package chatbot.tasks;

/**
 * The class represents generic task like that
 * of the real world with completion status and task name
 * associated.
 */
public abstract class Task {
    private boolean isComplete = false;
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * The method marks this task as completed
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * The method marks this task as incomplete
     */
    public void unmark() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public String getTaskName() {
        return this.taskName;
    }

    public int getStatus() {
        return this.isComplete ? 1 : 0;
    }

    public abstract String save();
}
