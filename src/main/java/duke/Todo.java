package duke;

/**
 * Represents a Task to do. A Todo object is Task with a due date.
 */
public class Todo extends Task{
    public Todo(String desc, char taskType) {
        super(desc, taskType);
    }

    public Todo(String desc, char completed, char taskType) {
        super(desc, completed, taskType);
    }

    /**
     * Creates a Todo object based on a given String which describes a Todo object.
     * @param desc String description of a Todo.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Creates a Todo instance that is identical to a given Todo object, and then marked as complete.
     * @return An identical Todo object that is marked as complete.
     */
    protected Todo performTask() {
        return new Todo(this.getDesc(), 'X', this.getTaskType());
    }

    /**
     * Creates a Todo instance that is identical to a given Todo object, and then marked as incomplete.
     * @return An identical Todo object that is marked as incomplete.
     */
    protected Todo undoTask() {
        return new Todo(this.getDesc(), this.getTaskType());
    }
}
