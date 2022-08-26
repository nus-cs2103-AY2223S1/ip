package luffy;

/**
 * Task abstract class to represent a task. Tasks consist of Deadlines, Events and Todos.
 *
 * @author Silas Tay A0233425M
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String by;
    protected String at;

    /**
     * Abstract constructor for Task.
     *
     * @param description Description for Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks Task as completed.
     */
    public void markCompleted() {
        this.isDone = true;
    }

    /**
     * Marks Task as uncompleted.
     */
    public void markUncompleted() {
        this.isDone = false;
    }

    /**
     * Returns if the Task description contains Query String.
     *
     * @param query Query String
     * @return If the Task contains query String
     */
    public boolean doesContain(String query) {
        return this.description.contains(query);
    }
}
