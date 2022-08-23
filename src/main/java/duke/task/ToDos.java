package duke.task;

public class ToDos extends Task {
    public ToDos(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "todo " + super.toSaveString();
    }
}
