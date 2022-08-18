public class Todo extends Task {
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Shows the todo name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
