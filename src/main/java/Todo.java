public class Todo extends Task {
    public Todo(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
