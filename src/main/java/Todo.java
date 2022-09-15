public class Todo extends Task{

    /**
     * Constructor for the Todo task.
     * @param name Input name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Todo task.
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatus(), name);
    }

    @Override
    public String changeFormat() {
        return String.format("T | %s | %s", getStatus(), name);
    }
}
