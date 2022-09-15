package chacha.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private String description;
    private boolean isDone;
    private String type;

    /**
     * Constructor for todo.
     * 
     * @param description Description of the todo.
     *
     */
    public Todo(String description) {
        this.description = description;
        this.type = "T";
    }

    /**
     * Marks todo as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks todo as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /** 
     * Gets status icon of todo.
     * 
     * @return X if done and empty string if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** 
     * Gets description of todo.
     * 
     * @return Description of todo.
     */
    public String getDescription() {
        return description;
    }

    /** 
     * Gets type of task.
     * 
     * @return Type of task.
     */
    public String getType() {
        return type;
    }

    /** 
     * Formats todo into a readable string.
     * 
     * @return Todo as a string.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon() + "] " + description;
    }
     
}
