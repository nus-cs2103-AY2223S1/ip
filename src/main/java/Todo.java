public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String tofileString() {
        return "T|" + super.tofileString();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
