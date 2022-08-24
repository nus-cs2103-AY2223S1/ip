package Duke;

/**
 * An abstract class with parameters task and done
 */
abstract public class Task {
    private String task;
    private boolean done;

    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * @return a string of the text
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    /**
     * @return a string of the icom
     */
    public String getStatusIcon() {
        return this.done ? "[X]" : "[ ]";
    }

    /**
     * @return a string of the task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * This sets the parameter done to true
     */
    public void markTaskAsDone() {
        done = true;
    }

    /**
     * This sets the parameter done to false
     */
    public void unMarkTaskAsDone() {
        done = false;
    }

    /**
     * This returns an int
     */
    public int getDone() {
        return done ? 1 : 0;
    }

    abstract char getType();

    abstract String getOriginalDetail();

    abstract String getFormattedDetail();
}
