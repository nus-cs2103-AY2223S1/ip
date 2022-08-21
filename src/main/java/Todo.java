public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[T]" + s;
    }
}
