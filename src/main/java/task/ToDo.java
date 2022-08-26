package task;

import task.Task;

/**
 * This class encapsulates the idea of a todo.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * @param description what the todo is
     * @param status whether it has been completed
     */
    public ToDo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public String getDescription() {
        String status = super.getStatus() ? "T" : "F";
        return "T | " + status + " | " + super.toString() +  "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.toString();
    }
}