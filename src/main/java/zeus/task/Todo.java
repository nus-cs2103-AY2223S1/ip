package zeus.task;

/**
 * Todo class that inherits from Task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description a String that represents the description for the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns Todo as a String formatted to be a line in file.
     *
     * @return String representing todo.
     */
    @Override
    public String getFileFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
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
