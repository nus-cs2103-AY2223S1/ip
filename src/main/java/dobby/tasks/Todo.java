package dobby.tasks;

/**
 * Todo is a class which is a task to be done.
 */
public class Todo extends Task {
    /**
     * Constructor of the Todo class.
     *
     * @param s Description of the todo task.
     */
    public Todo(String s) {
        super(s);
    }

    /**
     * Constructor of the Todo class.
     *
     * @param s Description of the todo task.
     * @param isDone Completion status of the todo task.
     */
    public Todo(String s, boolean isDone) {
        super(s, isDone);
    }

    /**
     * Returns the formatted Todo task to be saved in the file.
     *
     * @return String of todo task.
     */
    @Override
    public String toPrint() {
        return "T" + super.toPrint();
    }

    /**
     * Returns the formatted Todo task to be printed out.
     *
     * @return String of todo task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
