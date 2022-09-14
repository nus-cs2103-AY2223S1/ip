package duke;

/**
 * The class is an abstract class with parameters task and done
 *
 * @author LimWeiJun
 */
abstract public class Task {
    private String task;
    private boolean isDone;

    /**
     * A constructor that takes in two parameters
     *
     * @param task of type String
     * @param done of type boolean
     */
    Task(String task, boolean done) {
        this.task = task;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    /**
     * Gets the status icon of a task being marked or unmarked
     *
     * @return a string of the icon
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the name of the task
     *
     * @return a string of the task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Marks the task
     */
    public void setTaskAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task
     */
    public void setTaskAsUnDone() {
        isDone = false;
    }

    /**
     * Returns 1 if the task is marked and 0 if it is not
     *
     * @return of type int
     */
    public int getDone() {
        return isDone ? 1 : 0;
    }

    abstract char getType();

    abstract String getOriginalDetail();

    abstract String getFormattedDetail();

    abstract void updateDateTime(String newDateStr);
}
