package zeus.task;

/**
 * Todo class that inherits from Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo.
     *
     * @param description A String that represents the description for the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a copy of Todo.
     *
     * @param todo Todo to copy.
     */
    public Todo(Todo todo) {
        super(todo.getDescription(), todo.isDone);
    }

    /**
     * Returns Todo as a String formatted to be a line in file.
     *
     * @return String representing Todo.
     */
    @Override
    public String getFileFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns Task that represents this Todo to avoid casting in the copy constructor.
     *
     * @return Task that represents this Todo.
     */
    @Override
    public Task copy() {
        return new Todo(this);
    }

    /**
     * Returns a String that represents the Todo.
     *
     * @return a String that represents the Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
