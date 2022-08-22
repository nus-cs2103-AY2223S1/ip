public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.print(this);
        updateTasks();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}