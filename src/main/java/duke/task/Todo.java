package duke.task;

public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public Todo(String taskDescription, boolean isTaskDone) {
        super(taskDescription, isTaskDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + super.toFileString() + "\n";
    }
}
