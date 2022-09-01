package bro.task;

public class Todo extends Task {

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

