package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String savedString() {
        String status = isDone ? "1" : "0";
        return String.format("T | %s | %s", status, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getStatusIcon(), description);
    }
}
