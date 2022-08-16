public class Todo extends Task {

    public Todo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
