package task;

import task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
