package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String encodeToString() {
        String taskStatus = isDone ? "1" : "0";
        return String.format("T | %s | %s ", taskStatus, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getStatusIcon(), description);
    }
}
