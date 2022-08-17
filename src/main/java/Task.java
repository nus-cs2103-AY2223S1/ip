/**
 * The class represents generic task like that
 * of the real world with completion status and task name
 * associated.
 */
public class Task {
    private boolean completed = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * The method marks this task as completed
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * The method marks this task as incomplete
     */
    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
