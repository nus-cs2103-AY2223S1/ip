public class Todo extends Task {
    protected boolean Todo = true;

    public Todo(String description) {
           super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
