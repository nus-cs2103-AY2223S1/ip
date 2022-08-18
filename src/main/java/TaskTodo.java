public class TaskTodo extends Task {

    public TaskTodo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
