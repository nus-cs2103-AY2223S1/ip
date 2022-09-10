package duke.task;

/**
 * Encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isHighPriority;

    /**
     * Constructs an instance of a task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isHighPriority = false;
    }

    /**
     * Obtains icon used in string representation.
     * @return Icon for completed status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setPriority() {
        this.isHighPriority = true;
    }

    public boolean checkDone() {
        return isDone;
    }

    public boolean checkPriority() {
        return isHighPriority;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done and prints message.
     * @return Output message.
     */
    public String markAsDone() {
        this.isDone = true;
        String output = "Nice! I've marked this task as done:\n";
        output += this.toString();
        return output;
    }

    /**
     * Marks task as not done and prints message.
     * @return Output message.
     */
    public String markNotDone() {
        this.isDone = false;
        String output = "OK, I've marked this task as not done yet:\n";
        output += this.toString();
        return output;
    }

    public String markAsPriority() {
        this.isHighPriority = true;
        String output = "OK, I've marked this task as high priority:\n";
        output += this.toString();
        return output;
    }

    /**
     * Returns string representation of task.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}