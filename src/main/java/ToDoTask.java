/**
 * A task that is not time sensitive.
 */
public class ToDoTask extends Task{

    /**
     * Constructor for an todo task.
     * @param description A description of the todo
     */
    public ToDoTask(String description) {
        super(description, TaskType.TODO);
    }

    public ToDoTask(String description, boolean isMarked) {
        super(description, TaskType.TODO, isMarked);
    }
}
