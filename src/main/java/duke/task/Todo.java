package duke.task;

public class Todo extends Task {

    public Todo(TaskType type, String name, boolean isMarked) {
        super(type, name, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
