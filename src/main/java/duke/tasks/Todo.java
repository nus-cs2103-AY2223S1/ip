package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", super.getTaskIcon(), super.toString());
    }
}
