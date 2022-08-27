package task;

import task.Task;

public class Todo extends Task {


    /**
     * Creates a new Todo.
     * @param description The activity that needs to be done.
     */
    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
