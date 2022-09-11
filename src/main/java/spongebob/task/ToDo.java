package spongebob.task;

/**
 * Represents a class for to to-do tasks.
 */
public class ToDo extends Task {
    /**
     * Returns an instance of ToDo task.
     * @param isDone Completion status of task.
     * @param description Description of task.
     */
    public ToDo(String isDone, String description) {
        super(description, isDone.equals("1"));
    }

    /**
     * Returns string format of todo for saving purpose.
     * @return String format of todo.
     */
    @Override
    public String toStringSaveFormat() {
        return String.format("T,%s,%s\n", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Return string representation of todo.
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
