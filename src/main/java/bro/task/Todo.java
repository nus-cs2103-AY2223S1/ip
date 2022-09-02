package bro.task;

public class Todo extends Task {

    /**
     * Constructor of the Todo class.
     * @param description Gets the description from the super class.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "bro.task.Todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

