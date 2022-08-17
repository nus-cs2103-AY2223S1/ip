public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
