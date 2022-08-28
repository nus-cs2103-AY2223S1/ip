package duke.tasks;

/**
 * Tasks that can be marked as done or not done
 */
public abstract class Task {

    /** Description of what this task entails */
    protected String description;

    /** A symbol indicating what kind of task this is */
    protected final char typeSymbol;

    /** Is this task done? */
    protected boolean isDone;

    /**
     * Constructs a new task with the given description
     * @param description The task description
     * @param typeSymbol The symbol to indicate type
     */
    public Task(String description, char typeSymbol) {
        this.description = description;
        this.typeSymbol = typeSymbol;
        this.isDone = false;
    }

    /**
     * Marks this task as done
     * @return The task
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks this task as not done
     * @return The task
     */
    public Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    public char getTypeSymbol() {
        return typeSymbol;
    }

    /**
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    /**
     * @return The task type, status and description
     */
    @Override
    public String toString() {
        return "[" + getTypeSymbol() + "][" + getStatusIcon() + "] " + getDescription();
    }
}