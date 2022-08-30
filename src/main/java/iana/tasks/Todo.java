package iana.tasks;

/**
 * Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     * @param task full user input for todo description.
     * @param isCompleted true if todo is completed.
     */
    public Todo(String task, boolean isCompleted) {
        super(task, "todo", isCompleted);
    }

    /**
     * Returns string representation of todo to be stored in storage.
     */
    @Override
    public String toFileData() {
        return "T | " + super.toFileData();
    }

    /**
     * Returns string representation of todo.
     */
    @Override
    public String toString() { 
        return String.format("[T]%s", super.toString());
    }
}
