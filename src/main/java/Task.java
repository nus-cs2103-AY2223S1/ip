/** Task abstract class to represent a task. Tasks consist of Deadlines, Events and Todos.
 * @author Silas Tay A0233425M
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

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
}
