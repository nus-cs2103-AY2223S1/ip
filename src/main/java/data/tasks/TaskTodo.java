package data.tasks;

public class TaskTodo extends Task {
    private static final long serialVersionUID = 21L;

    public TaskTodo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
