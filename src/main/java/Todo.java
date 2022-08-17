public class Todo extends Task {
    public Todo(String taskItem) {
        super(taskItem);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
