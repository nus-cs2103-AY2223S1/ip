package duke.task;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public ToDo(String title, Boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    public String toSaveString() {
        return "T|" + super.toSaveString();
    }
}
