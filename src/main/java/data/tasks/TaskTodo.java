package data.tasks;

public class TaskTodo extends Task {

    public TaskTodo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
