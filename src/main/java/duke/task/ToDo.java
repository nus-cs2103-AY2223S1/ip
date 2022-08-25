package duke.task;

public class ToDo extends Task {
    public static final String TODO_REP = "T";

    public ToDo(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "[" + TODO_REP + "]" + super.toString();
    }

    @Override
    public String toStorage() {
        return TODO_REP + super.toStorage();
    }
}
