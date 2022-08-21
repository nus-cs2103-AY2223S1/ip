/**
 * The Task class is an abstract class which is inherited by
 * Todo, Deadline and Event.
 * @author Sheryl-Lynn Tan (G11)
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract int[] getDate();

    public void setCompleted() {
        this.isDone = true;
    }

    public void setIncomplete() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}