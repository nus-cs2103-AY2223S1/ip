package duke.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String taskMemo() {
        return "T" + super.taskMemo();
    }
}
