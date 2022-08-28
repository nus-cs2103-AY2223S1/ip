package chatbot.tasks;

/**
 * The class represents generic task like that
 * of the real world with completion status and task name
 * associated.
 */
public abstract class Task {
    private boolean isComplete = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.isComplete = true;
    }

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
