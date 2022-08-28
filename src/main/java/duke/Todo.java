package duke;

/**
 * Represents a Todo Task
 */
public class Todo extends Task {

    /**
     * Constructor for Todo for stored tasks.
     * @param taskName name of task.
     * @param isDone to set marked tasks (for storage retrieval).
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName.trim(), isDone);
    }

    /**
     * Constructor for Todo.
     * @param taskName name of task.
     */
    public Todo(String taskName) { super(taskName.trim(), false); }

    /**
     * Converts Todo object to string for file storage.
     * @return String for file storage.
     */
    @Override
    public String taskToFileString() {
        return " T " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName;
    }

    /**
     * Converts Todo object to string for printing.
     */
    @Override
    public String toString() {
        return "[T]" + (this.done ? "[X] " : "[ ] ") + this.taskName;
    }
}
