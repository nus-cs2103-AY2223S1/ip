package duke.tasks;

public class Todo extends Task {
    private static final String TASK_SYMBOL = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.TASK_SYMBOL, super.toString());
    }

    @Override
    public String getType() {
        return Todo.TASK_SYMBOL;
    }

    @Override
    public String getDate() {
        return " ";
    }
}
