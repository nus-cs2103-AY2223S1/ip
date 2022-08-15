
/**
 * Encapsulates a Task. Task supports marking as done, unmarking,
 * and a string representation.
 */
public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        /**
         * Constructor for Task class. Sets the task name.
         *
         * @param name The task name.
         */
        this.name = name;
    }

    public void markAsDone() {
        /**
         * Mark this task as done.
         */
        this.isDone = true;
    }

    public void unmarkAsDone() {
        /**
         * Mark this task as not done.
         */
        this.isDone = false;
    }

    public String toString() {
        /**
         * String representation of a task. Also indicates if the task is done.
         */
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
