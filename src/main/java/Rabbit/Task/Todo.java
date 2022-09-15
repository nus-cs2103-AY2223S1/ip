package rabbit.task;

/**
 * A subclass of task.
 */
public class Todo extends Task{
    /**
     * Constructor of a task to be done.
     * The task is created as not done.
     *
     * @param content the task to be done.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Constructor of a task to be done.
     *
     * @param content the task to be done.
     * @param isDone whether the task is done.
     */
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String toString() {
        return this.isDone() ? "[T][X] " + this.getContent() : "[T][ ] " + this.getContent();
    }
}
