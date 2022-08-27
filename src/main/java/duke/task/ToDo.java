package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X]" : "[T][ ]"); // mark done task with X
    }

    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Todo      | %s | %s", status, super.getDescription());
    }

    @Override
    public String taskType() {
        return "todo";
    }

}
