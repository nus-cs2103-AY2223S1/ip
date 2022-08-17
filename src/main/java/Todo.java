public class Todo extends Task {
    public Todo(String taskDescription, boolean completedTask) {
        super(taskDescription, completedTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
