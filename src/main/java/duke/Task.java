package duke;


/**
 * Class to represent a class that will store Task Objects
 * @author Ashiqur Rahman A02030107Y
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Todo class
     * @param description Details of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to check isDone
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to mark Task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to mark Task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }
}
