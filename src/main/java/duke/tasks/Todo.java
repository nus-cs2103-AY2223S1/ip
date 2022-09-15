package duke.tasks;

public class Todo extends Task {

    /**
     * Standard constructor for a todo task
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overloaded constructor that allows for creation of completed todos
     * @param description The description of the task
     * @param isDone Marks if task has been previously completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveString() {
        return "TODO,," + super.getSaveString() + "null";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
