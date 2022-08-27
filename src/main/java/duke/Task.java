package duke;

/**
 * Represents a task object that can be of type Event, Deadline or ToDo.
 * All tasks will minimally contain its taskname and whether it is done or not.
 *
 */
public abstract class Task {

    private final String taskname;
    private boolean isDone;

    public Task(String taskname) {
        this.taskname = taskname;
        this.isDone = false;
    }

    /**
     * Returns taskname.
     *
     * @return Taskname.
     */
    public String getTaskname() {
        return this.taskname;
    }

    /**
     * Marks this task as done.
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     *
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Provides the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "]" + " " + this.taskname;
    }

    /**
     * Provides the save file string representation of the task.
     *
     * @return Save file string representation of the task.
     */
    public String toSavedString() {
        int i = this.isDone ? 1 : 0;
        return "" + i + "#" + this.taskname;
    }


}
