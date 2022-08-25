package duke.task;

public class Todo extends Task {

    public Todo(TaskType taskType, String name, boolean isMarked) {
        super(taskType, name, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
