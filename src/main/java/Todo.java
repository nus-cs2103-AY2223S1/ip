public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
