
package duke;

public class Todo extends Task{

    boolean isDone;
    /**
     * initializes a todo object.
     * @param description string description of todo event.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * tells us if a particular task is done or not.
     * @return cross represents done, otherwise blank space means not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * marks a specific task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks a specific task object as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }



    /**
     * Returns string representation of the task
     * @return String representation of this task
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]"  + super.toString();
    }
}
