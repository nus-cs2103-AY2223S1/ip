public class Todo extends Task {
    public Todo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
