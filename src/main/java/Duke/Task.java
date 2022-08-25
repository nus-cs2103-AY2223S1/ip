package Duke;

/**
 * An abstract class with parameters task and done
 */
abstract public class Task {
    private String task;
    private boolean isDone;

    Task(String task, boolean done) {
        this.task = task;
        this.isDone = done;
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
        return this.isDone ? "[X]" : "[ ]";
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
    public void setTaskAsDone() {
        isDone = true;
    }

    /**
     * This sets the parameter done to false
     */
    public void setTaskAsUnDone() {
        isDone = false;
    }

    /**
     * This returns an int
     */
    public int getDone() {
        return isDone ? 1 : 0;
    }

    abstract char getType();

    abstract String getOriginalDetail();

    abstract String getFormattedDetail();
}
