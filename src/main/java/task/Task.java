package task;

/**
 * Represents a task in Duke
 */
public abstract class Task {
    /** Description of the task. */
    private final String description;

    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for a Task
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task has been done
     * @return a string representation of whether the task has been done or not,
     * X meaning the task is done, and blank meaning the task is not done
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }


    /**
     * toggles task completion status
     */
    public void toggleDone() {
        isDone = !isDone;
    }

    /**
     * stringify is used to store the tasks in a standard format in a storage file
     * @return string representation of a task to be stored
     */
    public String stringify() {
        return isDone ? "Y##" + this.description : "N##" + this.description;
    }

    /**
     * marks that a task is done and prints a line to indicate that the task is done
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * marks that a task is not done and prints a line to indicate that the task is not done yet
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    /**
     * toString is used to print out the task in an easily readable format
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.description);
    }
}
