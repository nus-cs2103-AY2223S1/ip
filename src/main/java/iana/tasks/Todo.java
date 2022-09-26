package iana.tasks;

import java.io.Serializable;

/**
 * Todo task.
 */
public class Todo extends Task implements Serializable {

    /**
     * Constructor for Todo class.
     * 
     * @param task full user input for todo description.
     * @param isCompleted true if todo is completed.
     */
    public Todo(String task, boolean isCompleted) {
        super(task, "todo", isCompleted);
    }

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
